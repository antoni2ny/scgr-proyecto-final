package dgtic.core.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tipo_reporte")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoReporte {

    @Id
    @Column(name = "id_tipo_reporte")
    private Long idTipoReporte;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;
}