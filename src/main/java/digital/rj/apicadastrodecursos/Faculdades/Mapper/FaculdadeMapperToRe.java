package digital.rj.apicadastrodecursos.Faculdades.Mapper;

import digital.rj.apicadastrodecursos.Faculdades.DTOs.FaculdadeDTO;
import digital.rj.apicadastrodecursos.Faculdades.DTOs.FaculdadeRequest;
import digital.rj.apicadastrodecursos.Faculdades.DTOs.FaculdadeResponse;
import org.springframework.stereotype.Component;

@Component
public class MapperToRe {

    public FaculdadeDTO toFaculdade(FaculdadeRequest request){
        var req =
                FaculdadeDTO
                        .builder()
                        .name(request.name())
                        .cnpj(request.cnpj())
                        .build();

        return req;
    }

    public FaculdadeResponse toResponse(FaculdadeDTO dto){

        var response =
                FaculdadeResponse
                        .builder()
                        .id(dto.getId())
                        .name(dto.getName())
                        .cursos(dto.getCursos())
                        .isActive(dto.isActive())
                        .build();

        return response;

    }
}
