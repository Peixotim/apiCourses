package digital.rj.apicadastrodecursos.Cursos.Controller;


import digital.rj.apicadastrodecursos.Cursos.DTOs.CursosRequest;
import digital.rj.apicadastrodecursos.Cursos.DTOs.CursosResponse;
import digital.rj.apicadastrodecursos.Cursos.Service.CursosService;
import digital.rj.apicadastrodecursos.Exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/courses")
public class CursosController {


    private CursosService service;

    public CursosController(CursosService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CursosResponse>> getAll(){
        var result = service.getAll();
        if(result.isEmpty()){
            throw new ResourceNotFoundException("Error: There are no courses registered in our database!");
        }
        return ResponseEntity.ok().body(result);
    }

    @PostMapping
    public ResponseEntity<CursosResponse> create(@RequestBody CursosRequest request){
        var result = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }


}
