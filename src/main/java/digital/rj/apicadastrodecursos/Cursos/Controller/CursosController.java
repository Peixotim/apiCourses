package digital.rj.apicadastrodecursos.Cursos.Controller;

import digital.rj.apicadastrodecursos.Cursos.DTOs.CursosRequest;
import digital.rj.apicadastrodecursos.Cursos.DTOs.CursosResponse;
import digital.rj.apicadastrodecursos.Cursos.Service.CursosService;
import digital.rj.apicadastrodecursos.infra.Exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/courses")
public class CursosController {

    private final CursosService service;

    public CursosController(CursosService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CursosResponse>> getAll() {
        var result = service.getAll();
        if (result.isEmpty()) {
            throw new ResourceNotFoundException("Error: There are no courses registered in our database!");
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<CursosResponse> create(@RequestBody CursosRequest request) {
        var result = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PostMapping("/{courseName}/assign/faculdade/{faculdadeName}")
    public ResponseEntity<?> assignToFaculdade(
            @PathVariable String courseName,
            @PathVariable String faculdadeName) {

        var result = service.assignToFaculdade(courseName, faculdadeName);
        return ResponseEntity.status(HttpStatus.OK).body(result.getBody());
    }

    @PostMapping("/{courseName}/assign/technical/{technicalName}")
    public ResponseEntity<?> assignToTechnical(
            @PathVariable String courseName,
            @PathVariable String technicalName) {

        var result = service.assignToTechicnial(courseName, technicalName);
        return ResponseEntity.status(HttpStatus.OK).body(result.getBody());
    }
}