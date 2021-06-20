import org.springframework.context.annotation.*;

@Configuration
@ComponentScans({
        @ComponentScan ("login"),
        @ComponentScan ("components"),
        @ComponentScan("registration"),
        @ComponentScan("work"),
        @ComponentScan("utils")})
public class GUIComponentsScan {

}
