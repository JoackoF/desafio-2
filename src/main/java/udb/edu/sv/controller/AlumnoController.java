package udb.edu.sv.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import udb.edu.sv.domain.Alumno;
import udb.edu.sv.service.IAlumnoService;
import udb.edu.sv.util.ResponseBuilder;
import udb.edu.sv.dto.ApiResponse;
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
        return ResponseEntity.ok(ResponseBuilder.success(lista, "Lista de alumnos obtenida con éxito"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Alumno>> crear(@RequestBody Alumno alumno) {
        Alumno guardado = service.save(alumno);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseBuilder.success(guardado, "Alumno creado correctamente"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Alumno>> obtenerPorId(@PathVariable Long id) {
        Alumno alumno = service.findById(id);
        if (alumno == null) {
            // Esto lo atrapará el GlobalExceptionHandler como un "Business logic error"
            throw new RuntimeException("No se encontró el alumno con ID: " + id);
        }
        return ResponseEntity.ok(ResponseBuilder.success(alumno));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> eliminar(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(ResponseBuilder.success(null, "Alumno eliminado"));
    }
}