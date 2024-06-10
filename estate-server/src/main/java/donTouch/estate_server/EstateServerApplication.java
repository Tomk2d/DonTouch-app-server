package donTouch.estate_server;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import donTouch.estate_server.estate.domain.EstateFundDetail;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EstateServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EstateServerApplication.class, args);
	}

}
