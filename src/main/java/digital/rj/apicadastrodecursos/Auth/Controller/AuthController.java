package digital.rj.apicadastrodecursos.Auth.Controller;

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
    public ResponseEntity<Map<String,String>> register(@RequestBody Map<String,String> request){
        var user = service.register(
                request.get("username"),
                request.get("mail"),
                request.get("password")
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                "status", "SUCCESS",
                "message", "User registered successfully",
                "email", user.getMail()
                ));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> request) {
        var token = service.login(request.get("mail"), request.get("password"));
        return ResponseEntity.ok(token);
    }
}
