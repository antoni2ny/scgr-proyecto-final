package dgtic.core.repository;

import dgtic.core.model.entity.EstatusReporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstatusReporteRepository extends JpaRepository<EstatusReporte, Long> {
}