package digital.rj.apicadastrodecursos.Auth.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;
@Entity
@Table(name = "tb_users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    @Column(name = "name",nullable = false)
    private String name;

    @NotBlank //Nao pode "vazio"
    @Column(name = "mail",nullable = false, unique = true)
    private String mail;

    @NotBlank
    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "roles", nullable = false)
    private UsersRoles roles = UsersRoles.USER;

}
