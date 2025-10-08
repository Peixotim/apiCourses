package digital.rj.apicadastrodecursos.Cursos.DTOs;

import digital.rj.apicadastrodecursos.Cursos.Model.Category;
import digital.rj.apicadastrodecursos.Cursos.Model.Type;
import lombok.Builder;

import java.util.UUID;

@Builder
public record CursosResponse(UUID id, String name, String description, Category category, Type type){
}
