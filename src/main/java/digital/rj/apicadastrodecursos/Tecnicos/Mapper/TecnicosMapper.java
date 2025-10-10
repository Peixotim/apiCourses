package digital.rj.apicadastrodecursos.Tecnicos.Mapper;

import digital.rj.apicadastrodecursos.Tecnicos.DTOs.Entity.TecnicosDTO;
import digital.rj.apicadastrodecursos.Tecnicos.DTOs.Request.TecnicosRequest;
import digital.rj.apicadastrodecursos.Tecnicos.DTOs.Response.TecnicosResponse;
import org.springframework.stereotype.Component;

@Component
public class TecnicosMapper {

    public TecnicosDTO toTecnico(TecnicosRequest request){
        var req =
                TecnicosDTO.builder()
                        .name(request.name())
                        .cnpj(request.cnpj())
                        .build();

        return req;
    }

    public TecnicosResponse toResponse(TecnicosDTO dto){
        var response =
                TecnicosResponse.builder()
                        .name(dto.getName())
                        .id(dto.getId())
                        .isActive(dto.isActive())
                        .cursos(dto.getCursos())
                        .cnpj(dto.getCnpj())
                        .build();

        return response;
    }
}
