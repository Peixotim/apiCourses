package digital.rj.apicadastrodecursos.Tecnicos.Controller;

import digital.rj.apicadastrodecursos.Tecnicos.DTOs.Request.TecnicosRequest;
import digital.rj.apicadastrodecursos.Tecnicos.Service.TecnicosService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;
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

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getByName(@PathVariable String name){
        return ResponseEntity.status(HttpStatus.OK).body(service.getName(name));
    }

    @GetMapping("/id/{name}")
    public ResponseEntity<?> getNameById(@PathVariable String name){
        return ResponseEntity.status(HttpStatus.OK).body(service.getID(name));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(service.getId(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody TecnicosRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteId(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(service.deleteById(id));
    }

    @DeleteMapping("/name/{name}")
    public ResponseEntity<?> deleteName(@PathVariable String name){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(service.deleteByName(name));
    }


}
