package digital.rj.apicadastrodecursos.Faculdades.DTOs;
import digital.rj.apicadastrodecursos.Cursos.Model.CursosModel;

import java.util.UUID;
import java.util.List;
public record Response(UUID id, String name, boolean isActive, String cnpj, List<CursosModel> cursos){
}
