package udb.edu.sv.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import udb.edu.sv.domain.Profesor;
import udb.edu.sv.service.IProfesorService;
import udb.edu.sv.util.ResponseBuilder;
import udb.edu.sv.dto.ApiResponse;
import udb.edu.sv.exception.BadRequestException;
import udb.edu.sv.exception.NotFoundException;

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

        if (lista.isEmpty()) {
            return ResponseEntity.ok(
                    ResponseBuilder.success(lista, "No hay profesores registrados")
            );
        }

        return ResponseEntity.ok(
                ResponseBuilder.success(lista, "Profesores recuperados con éxito")
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Profesor>> obtenerPorId(@PathVariable Long id) {

        if (id == null || id <= 0) {
            throw new BadRequestException("El ID debe ser mayor a 0");
        }

        Profesor profesor = service.findById(id);

        if (profesor == null) {
            throw new NotFoundException("Profesor no encontrado con ID: " + id);
        }

        return ResponseEntity.ok(ResponseBuilder.success(profesor));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Profesor>> crear(@Valid @RequestBody Profesor profesor) {

        if (profesor.getNombre() == null || profesor.getNombre().trim().isEmpty()) {
            throw new BadRequestException("El nombre del profesor es obligatorio");
        }

        Profesor guardado = service.save(profesor);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseBuilder.success(guardado, "Profesor registrado correctamente"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Profesor>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Profesor profesor
    ) {

        if (id == null || id <= 0) {
            throw new BadRequestException("El ID debe ser mayor a 0");
        }

        Profesor existente = service.findById(id);

        if (existente == null) {
            throw new NotFoundException("No se puede actualizar: el profesor no existe");
        }

        if (profesor.getNombre() == null || profesor.getNombre().trim().isEmpty()) {
            throw new BadRequestException("El nombre del profesor es obligatorio");
        }

        profesor.setId(id);

        Profesor actualizado = service.save(profesor);

        return ResponseEntity.ok(
                ResponseBuilder.success(actualizado, "Profesor actualizado correctamente")
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> eliminar(@PathVariable Long id) {

        if (id == null || id <= 0) {
            throw new BadRequestException("El ID debe ser mayor a 0");
        }

        Profesor profesor = service.findById(id);

        if (profesor == null) {
            throw new NotFoundException("Profesor no encontrado con ID: " + id);
        }

        service.delete(id);

        return ResponseEntity.ok(
                ResponseBuilder.success(null, "Profesor eliminado correctamente")
        );
    }
}