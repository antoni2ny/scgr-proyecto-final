package dgtic.core.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "dependencia")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dependencia {

    @Id
    @Column(name = "id_dependencia")
    private Long idDependencia;

    @Column(name = "nombre")
    private String nombre;
}