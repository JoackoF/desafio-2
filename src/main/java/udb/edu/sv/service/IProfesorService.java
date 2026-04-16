package udb.edu.sv.service;
import udb.edu.sv.domain.Profesor;
import java.util.List;

public interface IProfesorService {
    List<Profesor> findAll();
    Profesor findById(Long id);
    Profesor save(Profesor profesor);
    void delete(Long id);
}