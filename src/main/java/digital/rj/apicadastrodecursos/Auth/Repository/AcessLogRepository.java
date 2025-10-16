package digital.rj.apicadastrodecursos.Auth.Repository;

import digital.rj.apicadastrodecursos.Auth.Model.AccessLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AcessLogRepository extends JpaRepository<AccessLog, UUID> {
}
