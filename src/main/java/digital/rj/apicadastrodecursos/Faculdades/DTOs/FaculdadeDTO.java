package digital.rj.apicadastrodecursos.Faculdades.DTOs;

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
public class FaculdadeDTO {

    private UUID id;
    private String name;
    private boolean isActive;
    private String cnpj;
    private List<CursosModel> cursos;
}
