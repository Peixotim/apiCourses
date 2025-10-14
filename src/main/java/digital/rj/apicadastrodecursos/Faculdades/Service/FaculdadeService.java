package digital.rj.apicadastrodecursos.Faculdades.Service;

import digital.rj.apicadastrodecursos.Cursos.DTOs.CursosRequest;
import digital.rj.apicadastrodecursos.Cursos.DTOs.CursosResponse;
import digital.rj.apicadastrodecursos.Cursos.Mapper.CursosMapper;
import digital.rj.apicadastrodecursos.Cursos.Mapper.CursosMapperToEntity;
import digital.rj.apicadastrodecursos.infra.Exceptions.ResourceNotFoundException;
import digital.rj.apicadastrodecursos.Faculdades.DTOs.FaculdadeRequest;
import digital.rj.apicadastrodecursos.Faculdades.DTOs.FaculdadeResponse;
import digital.rj.apicadastrodecursos.Faculdades.Mapper.FaculdadeMapperToEntity;
import digital.rj.apicadastrodecursos.Faculdades.Mapper.FaculdadeMapperToRe;
import digital.rj.apicadastrodecursos.Faculdades.Repository.FaculdadeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@Service
public class FaculdadeService {

    private FaculdadeMapperToEntity mapperE;
    private FaculdadeMapperToRe mapperR;
    private FaculdadeRepository repository;
    private CursosMapper cursosMapper;
    private CursosMapperToEntity cursosMapperToEntity;

    public FaculdadeService(FaculdadeMapperToEntity mapperE, FaculdadeMapperToRe mapperR, FaculdadeRepository repository,CursosMapper cursosMapper,CursosMapperToEntity cursosMapperToEntity) {
        this.mapperE = mapperE;
        this.mapperR = mapperR;
        this.repository = repository;
        this.cursosMapper = cursosMapper;
        this.cursosMapperToEntity = cursosMapperToEntity;
    }

    @Transactional
    public FaculdadeResponse create(FaculdadeRequest request) {

        var req = mapperR.toFaculdade(request);
        var rep = mapperE.map(req);

        repository.save(rep);
        var result = mapperR.toResponse(mapperE.map(rep));
        return result   ;
    }

    public List<FaculdadeResponse> getAll(){

        var faculdades = repository.findAll();

        if(faculdades.isEmpty()){
            throw new ResourceNotFoundException("There are no colleges registered in the database !");
        }

        var retorno = faculdades.stream()
                .map(mapperE :: map)
                .map(mapperR ::toResponse)
                .toList();

        return retorno;
    }


    public UUID returnID(String name){
        var findByName = repository.findByName(name);
        if(findByName.isEmpty()){
            throw new ResourceNotFoundException("The College with that name does not exist in our database");
        }

        var id = findByName.get().getId();
        return id;
    }


    @Transactional
    public ResponseEntity<?> deleteByName(String name){

        var findByName = repository.findByName(name).orElseThrow();
        var getId = findByName.getId() ;
        repository.deleteById(getId);

        if (findByName == null) {
            throw new ResourceNotFoundException("The College with that name does not exist in our database");
        }else{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Map.of(
                    "Faculdade : " , findByName.getName(),
                    "CNPJ : : " , findByName.getCnpj(),
                    "Status : " , findByName.isActive(),
                    "Deleted : " , "YES"
            ));
        }
    }


    public List<CursosResponse> listCourses(String name){
        var findByName = repository.findByName(name);
        if(findByName.isEmpty()){
            throw new ResourceNotFoundException("The College with that name does not exist in our database");
        }else{
            var toDTO = findByName.map(mapperE::map);
            var coursesDTO = toDTO.get().getCursos().stream().map(cursosMapperToEntity::map).toList();
            var coursesResponse = coursesDTO.stream().map(cursosMapper::toResponse).toList();
            return coursesResponse;
        }
    }

    public FaculdadeResponse searchByName(String name){
        var search = repository.findByName(name).orElseThrow(() -> new ResourceNotFoundException("The College with that name does not exist in our database"));
        var toDTO = mapperE.map(search);
        var toResponse = mapperR.toResponse(toDTO);
        return toResponse;
    }


    public FaculdadeResponse searchById(UUID id){
        var search = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("The College with that name does not exist in our database"));
        var toDTO = mapperE.map(search);
        var toResponse = mapperR.toResponse(toDTO);
        return toResponse;
    }


    @Transactional
    public ResponseEntity<?> updateName(UUID id , String name){
       var search = repository.findById(id).orElseThrow(() ->  new ResourceNotFoundException("The College with that name does not exist in our database"));

       if(search != null){
           search.setName(name);
           repository.save(search);
           var toDto = mapperE.map(search);
           var toResponse = mapperR.toResponse(toDto);

           return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Map.of(
                   "College : " , toResponse.name(),
                   "CNPJ : : " , toResponse.cnpj(),
                   "Status : " , toResponse.isActive(),
                   "Courses : ",toResponse.cursos()
           ));
       }else{
           return ResponseEntity.badRequest().build();
       }
    }

    @Transactional
    public ResponseEntity<?> disabled(String name){
        var disabled = repository.findByName(name).orElseThrow(() -> new ResourceNotFoundException("The College with that name does not exist in our database") );
        if(disabled != null){
            disabled.setActive(false);
            repository.save(disabled);
            var toDto = mapperE.map(disabled);
            var toResponse = mapperR.toResponse(toDto);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Map.of(
                    "College : " , toResponse.name(),
                    "CNPJ : : " , toResponse.cnpj(),
                    "Status : " , toResponse.isActive(),
                    "Courses : ",toResponse.cursos()
            ));
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

}