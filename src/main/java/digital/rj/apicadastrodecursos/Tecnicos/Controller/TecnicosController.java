package digital.rj.apicadastrodecursos.Tecnicos.Controller;

import digital.rj.apicadastrodecursos.Tecnicos.DTOs.Request.TecnicosRequest;
import digital.rj.apicadastrodecursos.Tecnicos.Service.TecnicosService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("tec")
public class TecnicosController {


    private TecnicosService service;

    public TecnicosController(TecnicosService service){
        this.service = service;
    }
    @GetMapping
    public ResponseEntity<?> listAll(){
        return ResponseEntity.ok(service.getAll());
    }
    @PostMapping
    public ResponseEntity<?> create(@RequestBody TecnicosRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }
    @
}
