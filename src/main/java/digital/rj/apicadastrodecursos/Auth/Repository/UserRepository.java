package digital.rj.apicadastrodecursos.Auth.Repository;

import digital.rj.apicadastrodecursos.Auth.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
import java.util.Optional;
public interface UserRepository extends JpaRepository<UserModel,UUID> {
    Optional<UserModel> findByMail(String mail);
    boolean existsByMail(String mail);
}
