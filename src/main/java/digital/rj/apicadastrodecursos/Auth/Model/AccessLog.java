package digital.rj.apicadastrodecursos.Auth.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_acess_logs")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AccessLog {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String ipAddress;
    private String endpoint;
    private String method;
    private int statusCode;
    private boolean success;
    private LocalDateTime timestamp;
}
