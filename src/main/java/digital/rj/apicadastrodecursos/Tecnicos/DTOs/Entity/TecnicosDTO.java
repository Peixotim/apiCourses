package digital.rj.apicadastrodecursos.Tecnicos.DTOs.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TecnicosDTO {

    private UUID id;
    private String name;
    private boolean isActive = true;
}
