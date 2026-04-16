package udb.edu.sv.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import udb.edu.sv.domain.Materia;
import udb.edu.sv.repository.MateriaRepository;
import udb.edu.sv.service.IMateriaService;
import java.util.List;

@Service
public class MateriaServiceImpl implements IMateriaService {
    @Autowired
    private MateriaRepository repository;

    @Override public List<Materia> findAll() { return repository.findAll(); }
    @Override public Materia findById(Long id) { return repository.findById(id).orElse(null); }
    @Override public Materia save(Materia materia) { return repository.save(materia); }
    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar. La materia no existe");
        }
        repository.deleteById(id);
    }
}
