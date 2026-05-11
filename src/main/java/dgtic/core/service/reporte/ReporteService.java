package dgtic.core.service.reporte;

import dgtic.core.model.dto.ReporteDto;
import dgtic.core.model.entity.EstatusReporte;
import dgtic.core.model.entity.Reporte;
import dgtic.core.model.entity.TipoReporte;
import dgtic.core.model.entity.Usuario;
import dgtic.core.repository.EstatusReporteRepository;
import dgtic.core.repository.ReporteRepository;
import dgtic.core.repository.TipoReporteRepository;
import dgtic.core.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReporteService {

    private final ReporteRepository reporteRepository;
    private final UsuarioRepository usuarioRepository;
    private final EstatusReporteRepository estatusReporteRepository;
    private final TipoReporteRepository tipoReporteRepository;

    public ReporteService(ReporteRepository reporteRepository,
                          UsuarioRepository usuarioRepository,
                          EstatusReporteRepository estatusReporteRepository,
                          TipoReporteRepository tipoReporteRepository) {
        this.reporteRepository = reporteRepository;
        this.usuarioRepository = usuarioRepository;
        this.estatusReporteRepository = estatusReporteRepository;
        this.tipoReporteRepository = tipoReporteRepository;
    }

    public Page<Reporte> listarReportes(Pageable pageable) {
        return reporteRepository.findAll(pageable);
    }

    public Reporte guardarReporte(ReporteDto reporteDto) {
        Usuario usuario = usuarioRepository.findById(reporteDto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        EstatusReporte estatusReporte = estatusReporteRepository.findById(reporteDto.getIdEstatus())
                .orElseThrow(() -> new RuntimeException("Estatus no encontrado"));
        TipoReporte tipoReporte = tipoReporteRepository.findById(reporteDto.getIdTipoReporte())
                .orElseThrow(() -> new RuntimeException("Tipo de reporte no encontrado"));
        Reporte reporte;
        if (reporteDto.getIdReporte() != null) {
            reporte = reporteRepository.findById(reporteDto.getIdReporte())
                    .orElseThrow(() -> new RuntimeException("Reporte no encontrado"));
        } else {
            reporte = new Reporte();
        }
        reporte.setNombre(reporteDto.getNombre());
        reporte.setDescripcion(reporteDto.getDescripcion());
        reporte.setUsuario(usuario);
        reporte.setEstatusReporte(estatusReporte);
        reporte.setTipoReporte(tipoReporte);
        /*reporteRepository.save(reporte);*/
        return reporteRepository.save(reporte);
    }

    public ReporteDto buscarPorId(Long id) {
        Reporte reporte = reporteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reporte no encontrado"));
        ReporteDto dto = new ReporteDto();
        dto.setIdReporte(reporte.getIdReporte());
        dto.setNombre(reporte.getNombre());
        dto.setDescripcion(reporte.getDescripcion());
        dto.setIdUsuario(reporte.getUsuario().getIdUsuario());
        dto.setIdEstatus(reporte.getEstatusReporte().getIdEstatus());
        dto.setIdTipoReporte(reporte.getTipoReporte().getIdTipoReporte());
        return dto;
    }

    public void eliminarReporte(Long id) {
        Reporte reporte = reporteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reporte no encontrado"));
        reporteRepository.delete(reporte);
    }

    public List<Reporte> listarTodos() {
        return reporteRepository.findAll();
    }

    public List<Reporte> buscarReportes(String buscar) {
        return reporteRepository.findByNombreContainingIgnoreCase(buscar);
    }

}