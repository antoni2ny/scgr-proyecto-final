package dgtic.core.service.usuario;

import dgtic.core.model.dto.UsuarioDto;
import dgtic.core.model.entity.Dependencia;
import dgtic.core.model.entity.Usuario;
import dgtic.core.repository.DependenciaRepository;
import dgtic.core.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final DependenciaRepository dependenciaRepository;

    public UsuarioService(UsuarioRepository usuarioRepository,
                          DependenciaRepository dependenciaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.dependenciaRepository = dependenciaRepository;
    }

    public Page<Usuario> listarUsuarios(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }

    public void guardarUsuario(UsuarioDto usuarioDto) {
        Dependencia dependencia = dependenciaRepository.findById(usuarioDto.getIdDependencia())
                .orElseThrow(() -> new RuntimeException("Dependencia no encontrada"));
        Usuario usuario;
        if (usuarioDto.getIdUsuario() != null) {
            usuario = usuarioRepository.findById(usuarioDto.getIdUsuario())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        } else {
            usuario = new Usuario();
        }
        usuario.setNumeroEmpleado(usuarioDto.getNumeroEmpleado());
        usuario.setNombreCompleto(usuarioDto.getNombreCompleto());
        usuario.setDependencia(dependencia);
        if (usuarioDto.getPassword() != null && !usuarioDto.getPassword().isBlank()) {
            usuario.setPassword(usuarioDto.getPassword());
        }
        usuarioRepository.save(usuario);
    }

    public UsuarioDto buscarPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setIdUsuario(usuario.getIdUsuario());
        usuarioDto.setNumeroEmpleado(usuario.getNumeroEmpleado());
        usuarioDto.setNombreCompleto(usuario.getNombreCompleto());
        usuarioDto.setPassword("");
        usuarioDto.setIdDependencia(usuario.getDependencia().getIdDependencia());
        return usuarioDto;
    }

    public void eliminarUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuarioRepository.delete(usuario);
    }

    public List<Usuario> buscarUsuarios(String buscar) {
        return usuarioRepository.findByNombreCompletoContainingIgnoreCase(buscar);
    }
}