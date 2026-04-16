package udb.edu.sv.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import udb.edu.sv.domain.Profesor;
import udb.edu.sv.service.IProfesorService;
import udb.edu.sv.util.ResponseBuilder;
import udb.edu.sv.dto.ApiResponse;
import java.util.List;

@RestController
@RequestMapping("/api/profesores")
@Tag(name = "Profesores")
public class ProfesorController {

    @Autowired
    private IProfesorService service;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Profesor>>> listar() {
        List<Profesor> lista = service.findAll();
        return ResponseEntity.ok(ResponseBuilder.success(lista, "Profesores recuperados con éxito"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Profesor>> obtenerPorId(@PathVariable Long id) {
        Profesor profesor = service.findById(id);
        if (profesor == null) {
            throw new RuntimeException("Profesor no encontrado con ID: " + id);
        }
        return ResponseEntity.ok(ResponseBuilder.success(profesor));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Profesor>> crear(@RequestBody Profesor profesor) {
        Profesor guardado = service.save(profesor);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseBuilder.success(guardado, "Profesor registrado correctamente"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Profesor>> actualizar(@PathVariable Long id, @RequestBody Profesor profesor) {
        if (service.findById(id) == null) {
            throw new RuntimeException("No se puede actualizar: Profesor no existe");
        }
        profesor.setId(id);
        return ResponseEntity.ok(ResponseBuilder.success(service.save(profesor), "Profesor actualizado"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> eliminar(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(ResponseBuilder.success(null, "Profesor eliminado exitosamente"));
    }
}