package digital.rj.apicadastrodecursos.Cursos.Repository;

import digital.rj.apicadastrodecursos.Cursos.Model.CursosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface CursosRepository extends JpaRepository<CursosModel,UUID> {
    Optional<CursosModel> findByName(String name);
}
