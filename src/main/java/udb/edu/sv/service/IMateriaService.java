package udb.edu.sv.service;
import udb.edu.sv.domain.Materia;
import java.util.List;

public interface IMateriaService {
    List<Materia> findAll();
    Materia findById(Long id);
    Materia save(Materia materia);
    void delete(Long id);
}