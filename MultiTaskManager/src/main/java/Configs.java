import org.springframework.context.annotation.*;

@Configuration
@ComponentScans({
        @ComponentScan ("login"),
        @ComponentScan ("logout"),
        @ComponentScan("registration"),
        @ComponentScan("work"),
        @ComponentScan("dao"),
        @ComponentScan("utils")})
public class Configs {

}
