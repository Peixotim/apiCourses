package digital.rj.apicadastrodecursos.Tecnicos.Repository;

import digital.rj.apicadastrodecursos.Tecnicos.Model.TecnicosModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TecnicosRepository extends JpaRepository<TecnicosModel,UUID> {
}
