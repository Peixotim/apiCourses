package digital.rj.apicadastrodecursos.Auth.Controller;

import digital.rj.apicadastrodecursos.Auth.Model.UsersRoles;
import digital.rj.apicadastrodecursos.Auth.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RequestMapping("/auth")
@RestController
public class AuthController {

    private final UserService service;

    public AuthController(UserService service){
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String,String> request){
        var user = service.register(
                request.get("username"),
                request.get("mail"),
                request.get("password")
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        var token = service.login(request.get("mail"), request.get("password"));
        return ResponseEntity.ok(token);
    }

    @PostMapping("/alterRole")
    public ResponseEntity<?> alterRole(@RequestBody Map<String,String> request) {
            String mail = request.get("mail");
            UsersRoles role = UsersRoles.valueOf(request.get(request.get("role")).toUpperCase());
            var response = service.alterRole(mail,role);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

}

