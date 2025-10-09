package digital.rj.apicadastrodecursos.Tecnicos.Repository;

import digital.rj.apicadastrodecursos.Cursos.Model.CursosModel;
import digital.rj.apicadastrodecursos.Tecnicos.Model.TecnicosModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;
import java.util.Optional;
public interface TecnicosRepository extends JpaRepository<TecnicosModel,UUID> {
    Object getTecnicosModelByCursos(List<CursosModel> cursos);
    Optional<TecnicosModel> findByName(String name);
    Object getAllByCursos(List<CursosModel> cursos);

    Object deleteByName(String name);
}
