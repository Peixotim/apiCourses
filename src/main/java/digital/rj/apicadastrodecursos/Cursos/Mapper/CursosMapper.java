package digital.rj.apicadastrodecursos.Cursos.Mapper;

import digital.rj.apicadastrodecursos.Cursos.DTOs.CursosDTO;
import digital.rj.apicadastrodecursos.Cursos.DTOs.CursosRequest;
import digital.rj.apicadastrodecursos.Cursos.DTOs.CursosResponse;
import digital.rj.apicadastrodecursos.Tecnicos.DTOs.Entity.TecnicosDTO;
import digital.rj.apicadastrodecursos.Tecnicos.DTOs.Request.TecnicosRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;

@Component
public class CursosMapper {


    public CursosDTO toCursos(CursosRequest request) {
        var req =
                CursosDTO
                        .builder()
                        .name(request.name())
                        .type(request.type())
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
                            .category(dto.getCategory())
                            .type(dto.getType())
                            .build();

            return response;
        }
    }
