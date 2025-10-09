package digital.rj.apicadastrodecursos.Tecnicos.Service;

import digital.rj.apicadastrodecursos.Cursos.DTOs.CursosResponse;
import digital.rj.apicadastrodecursos.Cursos.Mapper.CursosMapper;
import digital.rj.apicadastrodecursos.Cursos.Mapper.MapperToEntity;
import digital.rj.apicadastrodecursos.Tecnicos.DTOs.Entity.TecnicosDTO;
import digital.rj.apicadastrodecursos.Tecnicos.DTOs.Request.TecnicosRequest;
import digital.rj.apicadastrodecursos.Tecnicos.DTOs.Response.TecnicosResponse;
import digital.rj.apicadastrodecursos.Tecnicos.Mapper.TecnicosEntityMapper;

import java.util.List;
import java.util.UUID;

import digital.rj.apicadastrodecursos.Tecnicos.Mapper.TecnicosMapper;
import digital.rj.apicadastrodecursos.Tecnicos.Model.TecnicosModel;
import digital.rj.apicadastrodecursos.Tecnicos.Repository.TecnicosRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TecnicosService {

    private TecnicosRepository repository;
    private TecnicosEntityMapper mapper;
    private TecnicosMapper mapperRes;
    private CursosMapper mapperCurses;
    private MapperToEntity mapperToEntity;

    public TecnicosService(TecnicosRepository repository, TecnicosEntityMapper mapper, TecnicosMapper mapperRes, CursosMapper cursesmapper, MapperToEntity mapperToEntity) {
        this.repository = repository;
        this.mapper = mapper;
        this.mapperRes = mapperRes;
        this.mapperCurses = cursesmapper;
        this.mapperToEntity = mapperToEntity;
    }

    @Transactional
    public TecnicosDTO create(TecnicosRequest request) {
        TecnicosModel model = new TecnicosModel();
        model.setName(request.name());
        var saved = repository.save(model);

        return mapper.map(saved);
    }


    public List<TecnicosResponse> getAll() {
        var listAll = repository.findAll();
        var listByDto = listAll.stream().map(mapper::map).toList();
        var listByResponse = listByDto.stream().map(mapperRes::toResponse).toList();
        return listByResponse;
    }


    //Pega o nome da empresa e retorna a lista de cursos
    public List<CursosResponse> listCourses(String name) {

        var findByName = repository
                .findByName(name)
                .orElseThrow(() -> new RuntimeException("Error Enterprise Technic is Not Found !")); //Pesquisa o Tecnico pelo nome

        var GetCourses = findByName.getCursos()
                .stream()
                .map(mapperToEntity::map)
                .map(mapperCurses::toResponse)
                .toList(); //Transforma em uma nova lista

        return GetCourses;
    }


    @Transactional
    public ResponseEntity<?> deleteByName(String name) {
        var technical = repository.findByName(name);
        if (repository.existsById(technical.get().getId())) {
            var delete = repository.deleteByName(name);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Techinical Deleted !");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Techinical Not Found !");
        }
    }

    @Transactional
    public ResponseEntity<?> deleteById(UUID id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Techinical Deleted !");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Techinical Not Found !");
        }
    }

    @Transactional
    public ResponseEntity<?> updateByName(UUID id, String name) {
        var techinical = repository.findById(id).orElseThrow(() -> new RuntimeException("Erro enterprise Not Found !"));
        techinical.setName(name);
        repository.save(techinical);
        return ResponseEntity.status(HttpStatus.OK).body("Actualized name success!");
    }

    @Transactional
    public ResponseEntity<?> disable(UUID id) {
        var findTech = repository.findById(id).orElseThrow(() -> new RuntimeException("Erro enterprise Not Found !"));

        findTech.setActive(false);
        repository.save(findTech);
        return ResponseEntity.ok("Enterprise Disabled!");
    }

    public UUID getID(String name){
        var findByName = repository.findByName(name);
        if(findByName.isPresent()){
            return findByName.get().getId();
        }else{
            throw new RuntimeException("Error enterprise not found !");
        }
}

public TecnicosResponse getId(UUID id){
        var find = repository.findById(id).orElseThrow(() -> new RuntimeException("Error Technical is Not Found !"));
        if(find != null){
            var findMapper = mapper.map(find);
            var findResponse = mapperRes.toResponse(findMapper);
            return findResponse;
        }
        throw new RuntimeException("Error !");
}


public TecnicosResponse getName(String name){
        var find = repository.findByName(name).orElseThrow(() -> new RuntimeException("Error Techinical is Not Found !"));

        if(find != null){
            var findMapper = mapper.map(find);
            var findResponse = mapperRes.toResponse(findMapper);
            return findResponse;
        }
        throw new RuntimeException("Error !");
}
}
