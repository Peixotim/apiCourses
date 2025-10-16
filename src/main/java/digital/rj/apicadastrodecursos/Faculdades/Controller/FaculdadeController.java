package digital.rj.apicadastrodecursos.Faculdades.Controller;

import digital.rj.apicadastrodecursos.Cursos.DTOs.CursosResponse;
import digital.rj.apicadastrodecursos.Faculdades.DTOs.FaculdadeRequest;
import digital.rj.apicadastrodecursos.Faculdades.DTOs.FaculdadeResponse;
import digital.rj.apicadastrodecursos.Faculdades.Service.FaculdadeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequestMapping("/college")
@RestController
public class FaculdadeController {

    private final FaculdadeService service;

    public FaculdadeController(FaculdadeService service) {
        this.service = service;
    }


    @GetMapping
    public ResponseEntity<List<FaculdadeResponse>> getAll() {
        var result = service.getAll();
        if (result.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{name}")
    public ResponseEntity<UUID> getId(@PathVariable String name) {
        var result = service.returnID(name);
        if (result == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/courses/{name}")
    public ResponseEntity<List<CursosResponse>> getCursos(@PathVariable String name) {
        var result = service.listCourses(name);
        if (result.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(result);
    }


    @PostMapping
    public ResponseEntity<FaculdadeResponse> create(@RequestBody FaculdadeRequest faculdadeRequest) {
        var created = service.create(faculdadeRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }


    @GetMapping("/search/{name}")
    public ResponseEntity<FaculdadeResponse> getByName(@PathVariable String name) {
        var result = service.searchByName(name);
        return ResponseEntity.ok(result);
    }


    @GetMapping("/id/{id}")
    public ResponseEntity<FaculdadeResponse> getById(@PathVariable UUID id) {
        var result = service.searchById(id);
        return ResponseEntity.ok(result);
    }


    @PutMapping("/{id}/update-name")
    public ResponseEntity<Map<String, Object>> updateName(
            @PathVariable UUID id,
            @RequestParam String name) {
        var updated = service.updateName(id, name);
        return ResponseEntity.status(HttpStatus.OK).body(
                Map.of(
                        "message", "Faculdade atualizada com sucesso",
                        "data", updated
                )
        );
    }

    @PatchMapping("/{name}/disable")
    public ResponseEntity<Map<String, Object>> disable(@PathVariable String name) {
        var disabled = service.disabled(name);
        return ResponseEntity.status(HttpStatus.OK).body(
                Map.of(
                        "message", "Faculdade desativada com sucesso",
                        "data", disabled
                )
        );
    }
    @DeleteMapping("/{name}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable String name) {
        var deleted = service.deleteByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(
                Map.of(
                        "message", "Faculdade excluída com sucesso",
                        "data", deleted
                )
        );
    }

}