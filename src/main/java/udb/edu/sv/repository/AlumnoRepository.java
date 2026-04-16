package udb.edu.sv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import udb.edu.sv.domain.Alumno;

public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
}