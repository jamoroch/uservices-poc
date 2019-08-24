package co.xapuka.demo.apollo;

import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan("co.xapuka.demo.apollo")
@EnableScheduling
@EnableAsync(proxyTargetClass = true)
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = {"co.xapuka.demo.apollo"})
@EntityScan(basePackages = {"co.xapuka.demo.apollo"})
@EnableSchedulerLock(defaultLockAtMostFor = "PT1S")
public class ApolloApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApolloApplication.class, args);
	}

}
