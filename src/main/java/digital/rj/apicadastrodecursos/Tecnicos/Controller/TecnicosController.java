package digital.rj.apicadastrodecursos.Tecnicos.Controller;

import digital.rj.apicadastrodecursos.Tecnicos.DTOs.Request.TecnicosRequest;
import digital.rj.apicadastrodecursos.Tecnicos.Service.TecnicosService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("techinical")
public class TecnicosController {

    private final TecnicosService service;

    public TecnicosController(TecnicosService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> listAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getByName(@PathVariable String name){
        return ResponseEntity.ok(service.getName(name));
    }

    @GetMapping("/find-id/{name}")
    public ResponseEntity<?> getNameById(@PathVariable String name){
        return ResponseEntity.ok(service.getID(name));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getId(@PathVariable UUID id){
        return ResponseEntity.ok(service.getId(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody TecnicosRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteId(@PathVariable UUID id){
        return service.deleteById(id);
    }

    @DeleteMapping("/name/{name}")
    public ResponseEntity<?> deleteName(@PathVariable String name){
        return service.deleteByName(name);
    }

    @GetMapping("/courses/{name}")
    public ResponseEntity<?> listCourse(@PathVariable String name){
        return ResponseEntity.ok(service.listCourses(name));
    }

    @PatchMapping("/disabled/{id}")
    public ResponseEntity<?> disabled(@PathVariable UUID id){
        return service.disable(id);
    }

    @PutMapping("/alter/{id}")
    public ResponseEntity<?> updateName(@PathVariable UUID id, @RequestBody String name){
        return service.updateByName(id, name);
    }

    @GetMapping("/cnpj/{cnpj}")
    public ResponseEntity<?> getEnterpriseByCnpj(@PathVariable String cnpj){
        return ResponseEntity.ok(service.getEnterpriseByCnpj(cnpj));
    }

}