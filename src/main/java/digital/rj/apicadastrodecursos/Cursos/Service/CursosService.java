package digital.rj.apicadastrodecursos.Cursos.Service;

import digital.rj.apicadastrodecursos.Cursos.DTOs.CursosRequest;
import digital.rj.apicadastrodecursos.Cursos.DTOs.CursosResponse;
import digital.rj.apicadastrodecursos.Cursos.Mapper.CursosMapper;
import digital.rj.apicadastrodecursos.Cursos.Mapper.CursosMapperToEntity;
import digital.rj.apicadastrodecursos.Cursos.Repository.CursosRepository;
import digital.rj.apicadastrodecursos.Exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class CursosService {


    private CursosRepository repository;
    private CursosMapper mapper;
    private CursosMapperToEntity entity;

    public CursosService(CursosRepository repository,CursosMapper mapper,CursosMapperToEntity entity){
        this.repository = repository;
        this.mapper  = mapper;
        this.entity = entity;
    }

    public List<CursosResponse> getAll(){
        var search = repository.findAll();
        var toEntity = search.stream().map(entity :: map).toList();
        var toResponse = toEntity.stream().map(mapper::toResponse).toList();
        if(search.isEmpty()){
            throw new ResourceNotFoundException("Error: There are no courses registered in our database!");
        }

        return toResponse;
    }


    @Transactional
    public CursosResponse create(CursosRequest request){

        var create = mapper.toCursos(request);
        var toEntity = entity.map(create);
        var saving = repository.save(toEntity);

        var response = mapper.toResponse(create);

        return response;
    }



}
