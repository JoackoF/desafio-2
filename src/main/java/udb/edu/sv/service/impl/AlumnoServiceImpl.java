package udb.edu.sv.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import udb.edu.sv.domain.Alumno;
import udb.edu.sv.repository.AlumnoRepository;
import udb.edu.sv.service.IAlumnoService;
import java.util.List;

@Service
public class AlumnoServiceImpl implements IAlumnoService {
    @Autowired
    private AlumnoRepository repository;

    @Override public List<Alumno> findAll() { return repository.findAll(); }
    @Override public Alumno findById(Long id) { return repository.findById(id).orElse(null); }
    @Override public Alumno save(Alumno alumno) { return repository.save(alumno); }
    @Override public void delete(Long id) { repository.deleteById(id); }
}