package digital.rj.apicadastrodecursos.Faculdades.Service;

import digital.rj.apicadastrodecursos.Exceptions.ResourceNotFoundException;
import digital.rj.apicadastrodecursos.Faculdades.DTOs.FaculdadeRequest;
import digital.rj.apicadastrodecursos.Faculdades.DTOs.FaculdadeResponse;
import digital.rj.apicadastrodecursos.Faculdades.Mapper.FaculdadeMapperToEntity;
import digital.rj.apicadastrodecursos.Faculdades.Mapper.FaculdadeMapperToRe;
import digital.rj.apicadastrodecursos.Faculdades.Model.FaculdadeModel;
import digital.rj.apicadastrodecursos.Faculdades.Repository.FaculdadeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

@Service
public class FaculdadeService {

    private FaculdadeMapperToEntity mapperE;
    private FaculdadeMapperToRe mapperR;
    private FaculdadeRepository repository;

    public FaculdadeService(FaculdadeMapperToEntity mapperE, FaculdadeMapperToRe mapperR, FaculdadeRepository repository) {
        this.mapperE = mapperE;
        this.mapperR = mapperR;
        this.repository = repository;
    }

    @Transactional
    public FaculdadeResponse create(FaculdadeRequest request) {

        FaculdadeModel model = new FaculdadeModel();
        model.setName(request.name());
        model.setCnpj(request.cnpj());
        repository.save(model);

        var mappingE = mapperE.map(model);
        var mappingR = mapperR.toResponse(mappingE);

        return mappingR;
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

    @Transactional
    public ResponseEntity<?> delete(String name){

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


}