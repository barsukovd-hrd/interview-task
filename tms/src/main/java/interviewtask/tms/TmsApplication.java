package interviewtask.tms;

import interviewtask.umsapi.config.annotation.EnableUserInfoClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableUserInfoClient
@SpringBootApplication
public class TmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TmsApplication.class, args);
	}
}
