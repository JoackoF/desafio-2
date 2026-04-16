package udb.edu.sv.service;

import udb.edu.sv.domain.Alumno;
import java.util.List;

public interface IAlumnoService {
    List<Alumno> findAll();
    Alumno save(Alumno alumno);
    Alumno findById(Long id);
    void delete(Long id);
}