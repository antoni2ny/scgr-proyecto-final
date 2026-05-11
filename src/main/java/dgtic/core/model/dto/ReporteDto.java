package dgtic.core.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReporteDto {

    private Long idReporte;

    @NotBlank(message = "El nombre del reporte es obligatorio")
    private String nombre;

    private String descripcion;

    @NotNull(message = "El usuario es obligatorio")
    private Long idUsuario;

    @NotNull(message = "El estatus es obligatorio")
    private Long idEstatus;

    @NotNull(message = "El tipo de reporte es obligatorio")
    private Long idTipoReporte;
}