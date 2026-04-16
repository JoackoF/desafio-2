package udb.edu.sv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import udb.edu.sv.domain.Profesor;

public interface ProfesorRepository extends JpaRepository<Profesor, Long> {
}