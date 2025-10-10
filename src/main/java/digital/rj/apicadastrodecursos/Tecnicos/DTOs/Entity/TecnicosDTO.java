package digital.rj.apicadastrodecursos.Tecnicos.DTOs.Entity;
import digital.rj.apicadastrodecursos.Cursos.Model.CursosModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TecnicosDTO {

    private UUID id;
    private String name;
    private boolean isActive = true;
    private String cnpj;
    private List<CursosModel> cursos;
}
