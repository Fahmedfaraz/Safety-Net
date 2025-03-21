
import org.springframework.boot.actuate.web.exchanges.HttpExchangeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.web.exchanges.InMemoryHttpExchangeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//
@Configuration
public class HttpTraceActuatorConfiguration {
	private static final Logger logger = LoggerFactory.getLogger(HttpTraceActuatorConfiguration.class);
    @Bean
    public HttpExchangeRepository createTraceRepository() {
    	  logger.info("This is an INFO message");
        return new InMemoryHttpExchangeRepository();
    }
}
