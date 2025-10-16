package digital.rj.apicadastrodecursos.Auth.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import digital.rj.apicadastrodecursos.Auth.Model.UserModel;
import digital.rj.apicadastrodecursos.Auth.Model.UsersRoles;
import digital.rj.apicadastrodecursos.Auth.Repository.UserRepository;
import digital.rj.apicadastrodecursos.infra.Exceptions.BadRequestException;
import digital.rj.apicadastrodecursos.infra.Exceptions.ConflictException;
import digital.rj.apicadastrodecursos.infra.Exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Value("${token.secret}")
    private String secretKey;

    private final Integer expire = 24; // Token válido por 24h


    public Map<String, Object> register(String name, String mail, String password) {

        if (repository.existsByMail(mail)) {
            throw new ConflictException("Error: The email already exists in our database.");
        }

        var user = UserModel.builder()
                .name(name)
                .mail(mail)
                .password(passwordEncoder.encode(password))
                .roles(UsersRoles.USER)
                .build();

        repository.save(user);

        return Map.of(
                "status", "SUCCESS",
                "message", "User registered successfully!",
                "data", Map.of(
                        "name", user.getName(),
                        "mail", user.getMail(),
                        "role", user.getRoles().name()
                )
        );
    }

    public Map<String, Object> login(String mail, String password) {
        var user = repository.findByMail(mail)
                .orElseThrow(() -> new ResourceNotFoundException("Error: User not found."));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadRequestException("Error: Incorrect password.");
        }

        var token = JWT.create()
                .withIssuer("API-RJ-DIGITAL")
                .withSubject(user.getMail())
                .withClaim("role", user.getRoles().name())
                .withExpiresAt(getExpirationDate())
                .sign(Algorithm.HMAC256(secretKey));

        return Map.of(
                "status", "SUCCESS",
                "message", "Login successful.",
                "data", Map.of(
                        "token", token,
                        "expires_in_hours", expire,
                        "user", Map.of(
                                "mail", user.getMail(),
                                "role", user.getRoles().name()
                        )
                )
        );
    }

    public Map<String, Object> alterRole(String mail, UsersRoles role) {
        var user = repository.findByMail(mail)
                .orElseThrow(() -> new BadRequestException("Error: Account not found."));

        if (role != UsersRoles.USER && role != UsersRoles.ADMIN) {
            throw new BadRequestException("Error: Invalid role specified.");
        }

        user.setRoles(role);
        repository.save(user);

        return Map.of(
                "status", "SUCCESS",
                "message", "User role updated successfully!",
                "data", Map.of(
                        "id", user.getId(),
                        "name", user.getName(),
                        "mail", user.getMail(),
                        "role", user.getRoles().name()
                )
        );
    }


    public Instant getExpirationDate() {
        return LocalDateTime.now()
                .plusHours(expire)
                .toInstant(ZoneOffset.of("-03:00"));
    }
}