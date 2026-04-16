package udb.edu.sv.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import udb.edu.sv.domain.Materia;
import udb.edu.sv.service.IMateriaService;
import udb.edu.sv.util.ResponseBuilder;
import udb.edu.sv.dto.ApiResponse;
import java.util.List;

@RestController
@RequestMapping("/api/materias")
@Tag(name = "Materias")
public class MateriaController {

    @Autowired
    private IMateriaService service;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Materia>>> listar() {
        List<Materia> lista = service.findAll();
        return ResponseEntity.ok(ResponseBuilder.success(lista, "Materias recuperadas con éxito"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Materia>> obtenerPorId(@PathVariable Long id) {
        Materia materia = service.findById(id);
        if (materia == null) {
            throw new RuntimeException("Materia no encontrada con ID: " + id);
        }
        return ResponseEntity.ok(ResponseBuilder.success(materia));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Materia>> crear(@RequestBody Materia materia) {
        Materia guardada = service.save(materia);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseBuilder.success(guardada, "Materia creada y vinculada correctamente"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Materia>> actualizar(@PathVariable Long id, @RequestBody Materia materia) {
        if (service.findById(id) == null) {
            throw new RuntimeException("Materia no existe");
        }
        materia.setId(id);
        return ResponseEntity.ok(ResponseBuilder.success(service.save(materia), "Materia actualizada"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> eliminar(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(ResponseBuilder.success(null, "Materia eliminada"));
    }
}