package file;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class HellofileappApplication {

	public static void main(String[] args) {
		SpringApplication.run(HellofileappApplication.class, args);
	}

	@Bean
	CommandLineRunner inet(StorageService storageService){
		return (String[] args) ->storageService.init();
	}

}
