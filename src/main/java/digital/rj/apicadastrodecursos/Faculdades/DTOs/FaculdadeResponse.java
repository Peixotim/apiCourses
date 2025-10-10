package digital.rj.apicadastrodecursos.Faculdades.DTOs;
import digital.rj.apicadastrodecursos.Cursos.Model.CursosModel;
import lombok.Builder;
import java.util.UUID;
import java.util.List;

@Builder
public record FaculdadeResponse(UUID id, String name, boolean isActive, String cnpj, List<CursosModel> cursos){
}
