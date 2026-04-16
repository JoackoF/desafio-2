package udb.edu.sv.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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

        if (lista.isEmpty()) {
            return ResponseEntity.ok(
                    ResponseBuilder.success(lista, "No hay materias registradas")
            );
        }

        return ResponseEntity.ok(
                ResponseBuilder.success(lista, "Materias recuperadas con éxito")
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Materia>> obtenerPorId(@PathVariable Long id) {

        if (id == null || id <= 0) {
            throw new RuntimeException("El ID debe ser mayor a 0");
        }

        Materia materia = service.findById(id);

        if (materia == null) {
            throw new RuntimeException("Materia no encontrada con ID: " + id);
        }

        return ResponseEntity.ok(ResponseBuilder.success(materia));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Materia>> crear(@Valid @RequestBody Materia materia) {

        if (materia.getNombre() == null || materia.getNombre().trim().isEmpty()) {
            throw new RuntimeException("El nombre de la materia es obligatorio");
        }

        Materia guardada = service.save(materia);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseBuilder.success(guardada, "Materia creada correctamente"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Materia>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Materia materia
    ) {

        if (id == null || id <= 0) {
            throw new RuntimeException("El ID debe ser mayor a 0");
        }

        Materia existente = service.findById(id);

        if (existente == null) {
            throw new RuntimeException("La materia no existe");
        }

        if (materia.getNombre() == null || materia.getNombre().trim().isEmpty()) {
            throw new RuntimeException("El nombre de la materia es obligatorio");
        }

        materia.setId(id);

        Materia actualizada = service.save(materia);

        return ResponseEntity.ok(
                ResponseBuilder.success(actualizada, "Materia actualizada correctamente")
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> eliminar(@PathVariable Long id) {

        if (id == null || id <= 0) {
            throw new RuntimeException("El ID debe ser mayor a 0");
        }

        Materia materia = service.findById(id);

        if (materia == null) {
            throw new RuntimeException("Materia no encontrada con ID: " + id);
        }

        service.delete(id);

        return ResponseEntity.ok(
                ResponseBuilder.success(null, "Materia eliminada correctamente")
        );
    }
}