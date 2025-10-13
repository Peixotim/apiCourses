package digital.rj.apicadastrodecursos.Faculdades.Controller;

import digital.rj.apicadastrodecursos.Faculdades.DTOs.FaculdadeResponse;
import digital.rj.apicadastrodecursos.Faculdades.Service.FaculdadeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RequestMapping("faculdade")
@RestController
public class FaculdadeController {

    private FaculdadeService service;

    public FaculdadeController(FaculdadeService service){
        this.service = service;
    }


    @GetMapping
    public List<FaculdadeResponse> getAll(){
        return service.getAll();
    }


}
