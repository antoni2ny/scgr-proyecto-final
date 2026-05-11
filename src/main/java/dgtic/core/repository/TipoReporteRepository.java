package dgtic.core.repository;

import dgtic.core.model.entity.TipoReporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoReporteRepository extends JpaRepository<TipoReporte, Long> {
}