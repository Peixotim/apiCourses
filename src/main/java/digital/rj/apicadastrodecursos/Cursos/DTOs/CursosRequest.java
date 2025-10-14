package digital.rj.apicadastrodecursos.Cursos.DTOs;

import digital.rj.apicadastrodecursos.Cursos.Model.Category;
import digital.rj.apicadastrodecursos.Cursos.Model.Type;
import lombok.Builder;

@Builder
public record CursosRequest(String name, String description, Category category, Type type){
}
