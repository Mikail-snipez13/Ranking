package mikail.Ranking;

import mikail.Ranking.Entity.Ranking;
import mikail.Ranking.Entity.RankingUser;
import mikail.Ranking.Repository.PageRepository;
import mikail.Ranking.Service.RoleService;
import mikail.Ranking.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
public class RankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(RankingApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(UserService service, RoleService roleService) {
		return args -> {
			roleService.create("ADMIN");
			roleService.create("USER");
			service.create("mikail", "mikail");
			service.addRole("mikail", "ADMIN");
		};
	}
}
