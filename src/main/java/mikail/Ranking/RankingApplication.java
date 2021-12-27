package mikail.Ranking;

import mikail.Ranking.Repository.PageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.*;
import java.util.Properties;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
public class RankingApplication {

	public static void main(String[] args) throws IOException {
		//Check for config file
		File config = new File("config/application.properties");
		//if (!configExists(config)) return; // for production use
		configExists(config); // only for testing!

		//Run Spring
		SpringApplication.run(RankingApplication.class, args);
	}

	private static boolean configExists(File config) throws IOException {
		//Create config file if not exists
		if (!config.exists()) {
			config.getParentFile().mkdir();
			config.createNewFile();

			//Set properties
			//values marked with "only for testing!" should be replaced with an empty string
			OutputStream os = new FileOutputStream(config);
			Properties prop = new Properties();
			prop.setProperty("spring.jpa.hibernate.ddl-auto", "update");
			prop.setProperty("spring.datasource.url", "jdbc:mysql://localhost:3306/spring"); // only for testing!
			prop.setProperty("spring.datasource.username", "mikail"); // only for testing!
			prop.setProperty("spring.datasource.password", "2Qj!3J#kuEX73n!FGNyrvoH%&6"); // only for testing!
			prop.setProperty("spring.datasource.driver-class-name", "com.mysql.jdbc.Driver");
			prop.setProperty("spring.jpa.show-sql", "true");

			prop.store(os, null);

			Logger logger = LoggerFactory.getLogger(RankingApplication.class);
			logger.error("application.properties file not found!");
			logger.error("Restart the application after filling out the config/application.properties!");
			logger.error("Shutting down...");

			return false;
		}
		else return true;
	}

}
