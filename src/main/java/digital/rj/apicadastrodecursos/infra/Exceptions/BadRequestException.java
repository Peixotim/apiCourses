package digital.rj.apicadastrodecursos.infra.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

    public BadRequestException(){
        super("BadRequest, unhandled error!");
    }

    public BadRequestException(String message) {
        super(message);
    }
}
