package dgtic.core.controller;

import dgtic.core.model.dto.ReporteDto;
import dgtic.core.model.entity.Reporte;
import dgtic.core.repository.EstatusReporteRepository;
import dgtic.core.repository.TipoReporteRepository;
import dgtic.core.repository.UsuarioRepository;
import dgtic.core.service.reporte.ReporteService;
import dgtic.core.util.RenderPagina;
import dgtic.core.util.ReportesPdf;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import dgtic.core.model.entity.Usuario;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import dgtic.core.service.bitacora.BitacoraService;

@Controller
public class ReporteController {

    private final ReporteService reporteService;
    private final UsuarioRepository usuarioRepository;
    private final EstatusReporteRepository estatusReporteRepository;
    private final TipoReporteRepository tipoReporteRepository;
    private final ReportesPdf reportesPdf;
    private final BitacoraService bitacoraService;

    public ReporteController(ReporteService reporteService,
                             UsuarioRepository usuarioRepository,
                             EstatusReporteRepository estatusReporteRepository,
                             TipoReporteRepository tipoReporteRepository,
                             ReportesPdf reportesPdf,
                             BitacoraService bitacoraService) {
        this.reporteService = reporteService;
        this.usuarioRepository = usuarioRepository;
        this.estatusReporteRepository = estatusReporteRepository;
        this.tipoReporteRepository = tipoReporteRepository;
        this.reportesPdf = reportesPdf;
        this.bitacoraService = bitacoraService;
    }

    @GetMapping("/reportes")
    public String listarReportes(@RequestParam(required = false) String buscar,
                                 @RequestParam(defaultValue = "0") int page,
                                 Model model) {
        model.addAttribute("buscar", buscar);

        if (buscar != null && !buscar.isBlank()) {
            model.addAttribute("reportes", reporteService.buscarReportes(buscar));
            return "reportes/lista";
        }
        Pageable pageable = PageRequest.of(page, 10);
        Page<Reporte> reportesPage = reporteService.listarReportes(pageable);
        RenderPagina<Reporte> renderPagina = new RenderPagina<>("/reportes", reportesPage);
        model.addAttribute("reportes", reportesPage.getContent());
        model.addAttribute("page", renderPagina);
        return "reportes/lista";
    }

    @GetMapping("/reportes/nuevo")
    public String nuevoReporte(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long numeroEmpleado = Long.parseLong(auth.getName());
        Usuario usuarioLogueado = usuarioRepository.findByNumeroEmpleado(numeroEmpleado)
                .orElseThrow();
        ReporteDto reporteDto = new ReporteDto();
        reporteDto.setIdUsuario(usuarioLogueado.getIdUsuario());
        model.addAttribute("reporte", reporteDto);
        model.addAttribute("usuarioLogueado", usuarioLogueado);
        model.addAttribute("estatuses", estatusReporteRepository.findAll());
        model.addAttribute("tiposReporte", tipoReporteRepository.findAll());

        return "reportes/formulario";
    }

    @PostMapping("/reportes/guardar")
    public String guardarReporte(@Valid @ModelAttribute("reporte") ReporteDto reporteDto,
                                 BindingResult bindingResult,
                                 Model model) {
        if (bindingResult.hasErrors()) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Long numeroEmpleado = Long.parseLong(auth.getName());
            Usuario usuarioLogueado = usuarioRepository.findByNumeroEmpleado(numeroEmpleado)
                    .orElseThrow();
            model.addAttribute("usuarioLogueado", usuarioLogueado);
            model.addAttribute("estatuses", estatusReporteRepository.findAll());
            model.addAttribute("tiposReporte", tipoReporteRepository.findAll());

            return "reportes/formulario";
        }
        boolean esNuevo = reporteDto.getIdReporte() == null;
        Reporte reporteGuardado = reporteService.guardarReporte(reporteDto);

        if (esNuevo) {
            bitacoraService.registrar(
                    "alta",
                    "reporte",
                    reporteGuardado.getIdReporte()
            );
        } else {
            bitacoraService.registrar(
                    "modificacion",
                    "reporte",
                    reporteGuardado.getIdReporte()
            );
        }

        return "redirect:/reportes";
    }

    @GetMapping("/reportes/{id}/editar")
    public String editarReporte(@PathVariable("id") Long id, Model model) {
        ReporteDto reporteDto = reporteService.buscarPorId(id);
        Usuario usuarioReporte = usuarioRepository.findById(reporteDto.getIdUsuario())
                .orElseThrow();
        model.addAttribute("reporte", reporteDto);
        model.addAttribute("usuarioLogueado", usuarioReporte);
        model.addAttribute("estatuses", estatusReporteRepository.findAll());
        model.addAttribute("tiposReporte", tipoReporteRepository.findAll());

        return "reportes/formulario";
    }

    @GetMapping("/reportes/{id}/eliminar")
    public String eliminarReporte(@PathVariable("id") Long id) {
        reporteService.eliminarReporte(id);
        bitacoraService.registrar(
                "baja",
                "reporte",
                id
        );
        return "redirect:/reportes";
    }

    @GetMapping("/reportes-pdf")
    public ModelAndView generarPdf() {
        ModelAndView mav = new ModelAndView(reportesPdf);
        mav.addObject("datos", reporteService.listarTodos());
        return mav;
    }
}