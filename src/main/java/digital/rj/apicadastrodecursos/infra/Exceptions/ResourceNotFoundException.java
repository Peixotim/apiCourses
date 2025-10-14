package digital.rj.apicadastrodecursos.infra.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {
        super("NOT FOUND !");
    }
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
