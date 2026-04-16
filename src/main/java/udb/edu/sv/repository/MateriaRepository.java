package udb.edu.sv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import udb.edu.sv.domain.Materia;

public interface MateriaRepository extends JpaRepository<Materia, Long> {
}