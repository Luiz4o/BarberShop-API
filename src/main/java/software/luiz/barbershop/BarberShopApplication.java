package software.luiz.barbershop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "software.luiz.barbershop")
public class BarberShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(BarberShopApplication.class, args);
	}

}
