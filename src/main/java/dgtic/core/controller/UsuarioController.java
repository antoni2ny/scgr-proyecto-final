package dgtic.core.controller;

import dgtic.core.model.dto.UsuarioDto;
import dgtic.core.model.entity.Usuario;
import dgtic.core.repository.DependenciaRepository;
import dgtic.core.service.usuario.UsuarioService;
import dgtic.core.util.RenderPagina;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final DependenciaRepository dependenciaRepository;

    public UsuarioController(UsuarioService usuarioService,
                             DependenciaRepository dependenciaRepository) {
        this.usuarioService = usuarioService;
        this.dependenciaRepository = dependenciaRepository;
    }

    @GetMapping("/usuarios")
    public String listarUsuarios(@RequestParam(required = false) String buscar,
                                 @RequestParam(defaultValue = "0") int page,
                                 Model model) {
        model.addAttribute("buscar", buscar);
        if (buscar != null && !buscar.isBlank()) {
            model.addAttribute("usuarios", usuarioService.buscarUsuarios(buscar));
            return "usuarios/lista";
        }
        Pageable pageable = PageRequest.of(page, 10);
        Page<Usuario> usuariosPage = usuarioService.listarUsuarios(pageable);
        RenderPagina<Usuario> renderPagina = new RenderPagina<>("/usuarios", usuariosPage);
        model.addAttribute("usuarios", usuariosPage.getContent());
        model.addAttribute("page", renderPagina);
        return "usuarios/lista";
    }

    @GetMapping("/usuarios/nuevo")
    public String nuevoUsuario(Model model) {
        model.addAttribute("usuario", new UsuarioDto());
        model.addAttribute("dependencias", dependenciaRepository.findAll());
        return "usuarios/formulario";
    }

    @PostMapping("/usuarios/guardar")
    public String guardarUsuario(@Valid @ModelAttribute("usuario") UsuarioDto usuarioDto,
                                 BindingResult bindingResult,
                                 Model model) {
        if (usuarioDto.getIdUsuario() == null) {
            if (usuarioDto.getPassword() == null || usuarioDto.getPassword().isBlank()) {
                bindingResult.rejectValue("password", "NotBlank.usuario.password",
                        "La contraseña es obligatoria");
            } else if (usuarioDto.getPassword().length() < 8 || usuarioDto.getPassword().length() > 20) {
                bindingResult.rejectValue("password", "Size.usuario.password",
                        "La contraseña debe tener entre 8 y 20 caracteres");
            }
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("dependencias", dependenciaRepository.findAll());
            return "usuarios/formulario";
        }
        try {
            usuarioService.guardarUsuario(usuarioDto);
        } catch (org.springframework.dao.DataIntegrityViolationException ex) {
            bindingResult.rejectValue("numeroEmpleado", "error.usuario",
                    "El número de empleado ya existe");
            model.addAttribute("dependencias", dependenciaRepository.findAll());
            return "usuarios/formulario";
        }
        return "redirect:/usuarios";
    }

    @GetMapping("/usuarios/{id}/editar")
    public String editarUsuario(@PathVariable("id") Long id, Model model) {
        model.addAttribute("usuario", usuarioService.buscarPorId(id));
        model.addAttribute("dependencias", dependenciaRepository.findAll());
        return "usuarios/formulario";
    }

    @GetMapping("/usuarios/{id}/eliminar")
    public String eliminarUsuario(@PathVariable("id") Long id,
                                  RedirectAttributes flash) {
        try {
            usuarioService.eliminarUsuario(id);
            flash.addFlashAttribute("success", "Usuario eliminado correctamente");
        } catch (org.springframework.dao.DataIntegrityViolationException ex) {
            flash.addFlashAttribute("error",
                    "No se puede eliminar el usuario porque tiene reportes asociados");
        }
        return "redirect:/usuarios";
    }
}