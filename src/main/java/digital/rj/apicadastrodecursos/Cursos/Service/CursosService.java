package digital.rj.apicadastrodecursos.Cursos.Service;

import digital.rj.apicadastrodecursos.Cursos.DTOs.CursosRequest;
import digital.rj.apicadastrodecursos.Cursos.DTOs.CursosResponse;
import digital.rj.apicadastrodecursos.Cursos.Mapper.CursosMapper;
import digital.rj.apicadastrodecursos.Cursos.Mapper.CursosMapperToEntity;
import digital.rj.apicadastrodecursos.Cursos.Repository.CursosRepository;
import digital.rj.apicadastrodecursos.Faculdades.Repository.FaculdadeRepository;
import digital.rj.apicadastrodecursos.Tecnicos.Repository.TecnicosRepository;
import digital.rj.apicadastrodecursos.infra.Exceptions.ConflictException;
import digital.rj.apicadastrodecursos.infra.Exceptions.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Map;
import java.util.List;


@Service
public class CursosService {

    private FaculdadeRepository repositoryFaculdade;
    private TecnicosRepository repositoryTecnico;
    private CursosRepository repository;
    private CursosMapper mapper;
    private CursosMapperToEntity entity;

    public CursosService(CursosRepository repository,CursosMapper mapper,CursosMapperToEntity entity,TecnicosRepository tecnicosRepository,FaculdadeRepository faculdadeRepository){
        this.repository = repository;
        this.mapper  = mapper;
        this.entity = entity;
        this.repositoryFaculdade = faculdadeRepository;
        this.repositoryTecnico = tecnicosRepository;
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

    @Transactional
    public ResponseEntity<?> assignToFaculdade(String courseName , String faculdadeName){

        var curso = repository.
                findByName(courseName).
                orElseThrow(() -> new ResourceNotFoundException("Error: Curso not found!"));

        var faculdade = repositoryFaculdade
                .findByName(faculdadeName)
                .orElseThrow(() -> new ResourceNotFoundException("Error: Faculdade not found!"));

        if(faculdade.getCursos().contains(curso)){
            throw new ConflictException("Error: This course is already assigned to this faculdade!");
        }

        faculdade.getCursos().add(curso);
        repositoryFaculdade.save(faculdade);

        return ResponseEntity.ok(Map.of(
                "message", "Curso vinculado à faculdade com sucesso!",
                "faculdade", faculdade.getName(),
                "curso", curso.getName()
        ));
    }
    @Transactional
    public ResponseEntity<?> assignToTechicnial(String courseName , String techinal){

        var curso = repository.
                findByName(courseName).
                orElseThrow(() -> new ResourceNotFoundException("Error: Curso not found!"));

        var tecnico = repositoryTecnico
                .findByName(techinal)
                .orElseThrow(() -> new ResourceNotFoundException("Error: Technical not found!"));

        if(tecnico.getCursos().contains(curso)){
            throw new ConflictException("Error: This course is already assigned to this faculdade!");
        }

        tecnico.getCursos().add(curso);
        repositoryTecnico.save(tecnico);

        return ResponseEntity.ok(Map.of(
                "message", "Curso vinculado o tecnico com sucesso!",
                "faculdade", tecnico.getName(),
                "curso", curso.getName()
        ));
    }

}
