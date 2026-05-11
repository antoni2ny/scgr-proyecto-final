package dgtic.core.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "estatus_reporte")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstatusReporte {

    @Id
    @Column(name = "id_estatus")
    private Long idEstatus;

    @Column(name = "nombre")
    private String nombre;
}