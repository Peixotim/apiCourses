package digital.rj.apicadastrodecursos.Tecnicos.DTOs.Response;

import digital.rj.apicadastrodecursos.Cursos.Model.CursosModel;
import lombok.Builder;
import java.util.UUID;
import java.util.List;

@Builder
public record TecnicosResponse(String name, String cnpj ,boolean isActive, UUID id, List<CursosModel> cursos){
}
