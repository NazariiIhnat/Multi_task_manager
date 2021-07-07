import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScans({
        @ComponentScan("login"),
        @ComponentScan ("logout"),
        @ComponentScan("registration"),
        @ComponentScan("task")})
public class BackendComponentsScan {
}
