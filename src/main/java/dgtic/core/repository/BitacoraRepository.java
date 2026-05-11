package dgtic.core.repository;

import dgtic.core.model.entity.Bitacora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BitacoraRepository extends JpaRepository<Bitacora, Long> {

    List<Bitacora> findAllByOrderByFechaHoraDesc();
    List<Bitacora> findByFechaHoraBetweenOrderByFechaHoraDesc(LocalDateTime fechaInicio,
                                                              LocalDateTime fechaFin);
}