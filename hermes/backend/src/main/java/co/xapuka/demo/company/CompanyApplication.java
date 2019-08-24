package co.xapuka.demo.company;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("co.xapuka.demo.company")
@EnableJpaAuditing
public class CompanyApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompanyApplication.class, args);
	}

	@Bean
	public ApplicationRunner demo( CompanyService companyService ){
		return (args) -> {
			companyService.create("Foo", "tagFoo");
			companyService.create("bar", "tagBar");
			companyService.create("FooBar");
		};
	}

}