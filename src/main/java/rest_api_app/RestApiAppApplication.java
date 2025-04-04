package rest_api_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {"Shared.Configuration", "Shared.Utils", "Shared.Filter"})
public class RestApiAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApiAppApplication.class, args);
	}
}
