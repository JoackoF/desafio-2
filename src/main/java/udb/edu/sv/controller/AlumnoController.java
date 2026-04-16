package udb.edu.sv.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import udb.edu.sv.domain.Alumno;
import udb.edu.sv.service.IAlumnoService;
import udb.edu.sv.util.ResponseBuilder;
import udb.edu.sv.dto.ApiResponse;
import udb.edu.sv.exception.BadRequestException;
import udb.edu.sv.exception.NotFoundException;

import java.util.List;

@RestController
@RequestMapping("/api/alumnos")
@Tag(name = "Alumnos")
public class AlumnoController {

    @Autowired
    private IAlumnoService service;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Alumno>>> listar() {
        List<Alumno> lista = service.findAll();

        if (lista.isEmpty()) {
            return ResponseEntity.ok(
                    ResponseBuilder.success(lista, "No hay alumnos registrados")
            );
        }

        return ResponseEntity.ok(
                ResponseBuilder.success(lista, "Lista de alumnos obtenida con éxito")
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Alumno>> obtenerPorId(@PathVariable Long id) {

        if (id == null || id <= 0) {
            throw new BadRequestException("El ID debe ser mayor a 0");
        }

        Alumno alumno = service.findById(id);

        if (alumno == null) {
            throw new NotFoundException("No se encontró el alumno con ID: " + id);
        }

        return ResponseEntity.ok(ResponseBuilder.success(alumno));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Alumno>> crear(@Valid @RequestBody Alumno alumno) {

        if (alumno.getNombre() == null || alumno.getNombre().trim().isEmpty()) {
            throw new BadRequestException("El nombre es obligatorio");
        }

        Alumno guardado = service.save(alumno);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseBuilder.success(guardado, "Alumno creado correctamente"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> eliminar(@PathVariable Long id) {

        if (id == null || id <= 0) {
            throw new BadRequestException("El ID debe ser mayor a 0");
        }

        Alumno alumno = service.findById(id);

        if (alumno == null) {
            throw new NotFoundException("No se encontró el alumno con ID: " + id);
        }

        service.delete(id);

        return ResponseEntity.ok(
                ResponseBuilder.success(null, "Alumno eliminado correctamente")
        );
    }
}