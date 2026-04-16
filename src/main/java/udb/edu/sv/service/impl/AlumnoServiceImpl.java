package udb.edu.sv.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import udb.edu.sv.domain.Alumno;
import udb.edu.sv.repository.AlumnoRepository;
import udb.edu.sv.service.IAlumnoService;

import java.util.List;

@Service
public class AlumnoServiceImpl implements IAlumnoService {

    @Autowired
    private AlumnoRepository repository;

    @Override
    public List<Alumno> findAll() {
        return repository.findAll();
    }

    @Override
    public Alumno findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Alumno save(Alumno alumno) {

        if (alumno == null) {
            throw new RuntimeException("El alumno no puede ser nulo");
        }

        return repository.save(alumno);
    }

    @Override
    @Transactional
    public void delete(Long id) {

        if (!repository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar. El alumno no existe");
        }

        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new RuntimeException("No se puede eliminar el alumno porque tiene relaciones asociadas");
        }
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public boolean tieneRelaciones(Long id) {
        try {
            repository.deleteById(id);
            return false;
        } catch (DataIntegrityViolationException ex) {
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}