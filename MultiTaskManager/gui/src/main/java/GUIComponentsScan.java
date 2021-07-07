import org.springframework.context.annotation.*;

@Configuration
@ComponentScans({
        @ComponentScan ("login"),
        @ComponentScan ("components"),
        @ComponentScan("registration"),
        @ComponentScan("TODO"),
        @ComponentScan("message"),
        @ComponentScan("task")})
public class GUIComponentsScan {

}
