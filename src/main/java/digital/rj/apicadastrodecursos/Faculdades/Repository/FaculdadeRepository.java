package digital.rj.apicadastrodecursos.Faculdades.Repository;


import digital.rj.apicadastrodecursos.Faculdades.Model.FaculdadeModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FaculdadeRepository extends JpaRepository<FaculdadeModel, UUID> {
}
