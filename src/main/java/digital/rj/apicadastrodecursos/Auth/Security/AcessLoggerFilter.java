
package digital.rj.apicadastrodecursos.Auth.Security;

import digital.rj.apicadastrodecursos.Auth.Model.AccessLog;
import digital.rj.apicadastrodecursos.Auth.Repository.AcessLogRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class AcessLoggerFilter extends OncePerRequestFilter {

    private final AcessLogRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        String ip = request.getRemoteAddr();
        String endpoint = request.getRequestURI();
        String method = request.getMethod();
        
        
        try{
            filterChain.doFilter(request,response);
            saveLog(ip,endpoint,method,response.getStatus(),true);
        }catch (Exception e){
        saveLog(ip,endpoint,method,HttpServletResponse.SC_INTERNAL_SERVER_ERROR,false);
        }
    }

    private void saveLog(String ip, String endpoint, String method, int status, boolean success) {
        AccessLog log = AccessLog.builder()
                .ipAddress(ip)
                .endpoint(endpoint)
                .method(method)
                .statusCode(status)
                .success(success)
                .timestamp(LocalDateTime.now())
                .build();

        repository.save(log);
    }
}
