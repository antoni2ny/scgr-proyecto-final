package dgtic.core.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto {

    private Long idUsuario;

    @NotNull(message = "El número de empleado es obligatorio")
    private Long numeroEmpleado;

    @NotBlank(message = "El nombre completo es obligatorio")
    @Size(max = 150, message = "El nombre completo no debe exceder 150 caracteres")
    private String nombreCompleto;

    @Size(max = 20, message = "La contraseña no debe exceder 20 caracteres")
    private String password;

    @NotNull(message = "La dependencia es obligatoria")
    private Long idDependencia;
}