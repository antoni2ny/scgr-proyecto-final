package dgtic.core.service.bitacora;

import dgtic.core.model.entity.Bitacora;
import dgtic.core.model.entity.Usuario;
import dgtic.core.repository.BitacoraRepository;
import dgtic.core.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class BitacoraService {

    private final BitacoraRepository bitacoraRepository;
    private final UsuarioRepository usuarioRepository;

    public void registrar(String accion, String tabla, Long idRegistro) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long numeroEmpleado = Long.parseLong(auth.getName());
        Usuario usuario = usuarioRepository.findByNumeroEmpleado(numeroEmpleado)
                .orElseThrow();

        Bitacora bitacora = new Bitacora();
        bitacora.setFechaHora(LocalDateTime.now());
        bitacora.setAccion(accion);
        bitacora.setTabla(tabla);
        bitacora.setIdRegistro(idRegistro);
        bitacora.setUsuario(usuario);
        bitacoraRepository.save(bitacora);
    }

    public List<Bitacora> listarBitacora() {
        return bitacoraRepository.findAllByOrderByFechaHoraDesc();
    }

    public List<Bitacora> buscarPorFechas(LocalDateTime fechaInicio,
                                          LocalDateTime fechaFin) {
        return bitacoraRepository.findByFechaHoraBetweenOrderByFechaHoraDesc(
                fechaInicio,
                fechaFin
        );
    }
}