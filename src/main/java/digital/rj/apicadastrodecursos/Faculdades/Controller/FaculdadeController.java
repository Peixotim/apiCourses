package digital.rj.apicadastrodecursos.Faculdades.Controller;

import digital.rj.apicadastrodecursos.Faculdades.DTOs.FaculdadeRequest;
import digital.rj.apicadastrodecursos.Faculdades.DTOs.FaculdadeResponse;
import digital.rj.apicadastrodecursos.Faculdades.Service.FaculdadeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("faculdade")
@RestController
public class FaculdadeController {

    private FaculdadeService service;

    public FaculdadeController(FaculdadeService service){
        this.service = service;
    }


    @GetMapping
    public ResponseEntity<List<FaculdadeResponse>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }


    @PostMapping
    public ResponseEntity<FaculdadeResponse> create(@RequestBody FaculdadeRequest faculdadeRequest){

            return service.create(faculdadeRequest);
    }

}
