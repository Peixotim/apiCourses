package digital.rj.apicadastrodecursos.Tecnicos.Service;

import digital.rj.apicadastrodecursos.Cursos.DTOs.CursosResponse;
import digital.rj.apicadastrodecursos.Cursos.Model.CursosModel;
import digital.rj.apicadastrodecursos.Tecnicos.DTOs.Entity.TecnicosDTO;
import digital.rj.apicadastrodecursos.Tecnicos.DTOs.Request.TecnicosRequest;
import digital.rj.apicadastrodecursos.Tecnicos.DTOs.Response.TecnicosResponse;
import digital.rj.apicadastrodecursos.Tecnicos.Mapper.TecnicosEntityMapper;
import java.util.List;

import digital.rj.apicadastrodecursos.Tecnicos.Mapper.TecnicosMapper;
import digital.rj.apicadastrodecursos.Tecnicos.Model.TecnicosModel;
import digital.rj.apicadastrodecursos.Tecnicos.Repository.TecnicosRepository;
import org.springframework.stereotype.Service;

@Service
public class TecnicosService {

    private TecnicosRepository repository;
    private TecnicosEntityMapper mapper;
    private TecnicosMapper mapperRes;

    public TecnicosService(TecnicosRepository repository,TecnicosEntityMapper mapper,TecnicosMapper mapperRes){
        this.repository = repository;
        this.mapper = mapper;
        this.mapperRes = mapperRes;
    }


    public TecnicosDTO create(TecnicosRequest request){
            TecnicosModel model = new TecnicosModel();
            model.setName(request.name());
            var saved = repository.save(model);

            return mapper.map(saved);
    }

    public List<TecnicosResponse> getAll(){
        var listAll = repository.findAll();
        var listByDto = listAll.stream().map(mapper::map).toList();
        var listByResponse = listByDto.stream().map(mapperRes::toResponse).toList();
        return listByResponse;
    }

    public List<CursosResponse> listCourses(){

    }
}
