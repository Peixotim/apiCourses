package digital.rj.apicadastrodecursos.Cursos.Mapper;

import digital.rj.apicadastrodecursos.Cursos.DTOs.CursosDTO;
import digital.rj.apicadastrodecursos.Cursos.DTOs.CursosRequest;
import digital.rj.apicadastrodecursos.Cursos.DTOs.CursosResponse;
import org.springframework.stereotype.Component;

@Component
public class CursosMapper {


    public CursosDTO toCursos(CursosRequest request) {
        var req =
                CursosDTO
                        .builder()
                        .name(request.name())
                        .type(request.type())
                        .description(request.description())
                        .category(request.category())
                        .build();

        return req;
    }
        public CursosResponse toResponse(CursosDTO dto){
            var response =
                    CursosResponse
                            .builder()
                            .id(dto.getId())
                            .name(dto.getName())
                            .description(dto.getDescription())
                            .category(dto.getCategory())
                            .type(dto.getType())
                            .build();

            return response;
        }
    }
