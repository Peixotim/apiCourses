package digital.rj.apicadastrodecursos.Faculdades.Controller;

import digital.rj.apicadastrodecursos.Cursos.DTOs.CursosResponse;
import digital.rj.apicadastrodecursos.Faculdades.DTOs.FaculdadeRequest;
import digital.rj.apicadastrodecursos.Faculdades.DTOs.FaculdadeResponse;
import digital.rj.apicadastrodecursos.Faculdades.Service.FaculdadeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("faculdade")
@RestController
public class FaculdadeController {

    private FaculdadeService service;

    public FaculdadeController(FaculdadeService service){
        this.service = service;
    }


    @GetMapping
    public ResponseEntity<List<FaculdadeResponse>> getAll(){
        var result = service.getAll();
        if(result.isEmpty()){
            ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{name}")
    public ResponseEntity<UUID> getId(@PathVariable String name){
        var result = service.returnID(name);
        if(result == null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/courses/{name}")
    public ResponseEntity<List<CursosResponse>> getCursos(@PathVariable String name){
        var result = service.listCourses(name);
        if(result.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(result);
    }


    @PostMapping
    public ResponseEntity<FaculdadeResponse> create(@RequestBody FaculdadeRequest faculdadeRequest){
            return ResponseEntity.ok(service.create(faculdadeRequest));
    }
}
