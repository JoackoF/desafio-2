package udb.edu.sv.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import udb.edu.sv.domain.Profesor;
import udb.edu.sv.repository.ProfesorRepository;
import udb.edu.sv.service.IProfesorService;
import java.util.List;

@Service
public class ProfesorServiceImpl implements IProfesorService {
    @Autowired
    private ProfesorRepository repository;

    @Override public List<Profesor> findAll() { return repository.findAll(); }
    @Override public Profesor findById(Long id) { return repository.findById(id).orElse(null); }
    @Override public Profesor save(Profesor profesor) { return repository.save(profesor); }
    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar. El profesor no existe");
        }
        repository.deleteById(id);
    }
}