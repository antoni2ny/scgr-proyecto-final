package dgtic.core.service.perfil;

import dgtic.core.model.entity.PerfilUsuario;
import dgtic.core.model.entity.Usuario;
import dgtic.core.repository.PerfilUsuarioRepository;
import dgtic.core.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PerfilUsuarioService {

    private final PerfilUsuarioRepository perfilUsuarioRepository;
    private final UsuarioRepository usuarioRepository;

    public PerfilUsuario obtenerPerfilUsuarioLogueado() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long numeroEmpleado = Long.parseLong(auth.getName());
        Usuario usuario = usuarioRepository.findByNumeroEmpleado(numeroEmpleado)
                .orElseThrow();

        return perfilUsuarioRepository.findByUsuarioIdUsuario(usuario.getIdUsuario())
                .orElse(null);
    }
}