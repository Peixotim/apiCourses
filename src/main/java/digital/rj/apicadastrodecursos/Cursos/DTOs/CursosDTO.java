package digital.rj.apicadastrodecursos.Cursos.DTOs;

import digital.rj.apicadastrodecursos.Cursos.Model.Category;
import digital.rj.apicadastrodecursos.Cursos.Model.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CursosDTO {

    private UUID id;
    private String name;
    private Category category;
    private Type type;
}
