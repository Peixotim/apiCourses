package digital.rj.apicadastrodecursos.Tecnicos.DTOs.Response;

import lombok.Builder;
import java.util.UUID;

@Builder
public record TecnicosResponse(String name, boolean isActive, UUID id){
}
