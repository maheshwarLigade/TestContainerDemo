package com.techwasti.testcontainer.endendexample;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.BindMode;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.containers.wait.strategy.WaitAllStrategy.Mode;
import org.testcontainers.images.builder.ImageFromDockerfile;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class EndendexampleApplicationTests {

	@Autowired
    EmployeeDAO employeeDAO;


     
	private static MySQLContainer mysqlContainer;

	private static RabbitMQContainer rabbitMQContainer;

	static{
		mysqlContainer=new MySQLContainer<>("mysql:latest")
		.withDatabaseName("demo")
		.withUsername("root")
		.withPassword("root")
		.withReuse(true);
		mysqlContainer.start();
     

		// GenericContainer genericContainer=new GenericContainer(new ImageFromDockerfile()
		// .withFileFromString("folder/someFile.txt", "hello")
		// .withFileFromClasspath("test.txt", "mappable-resource/test-resource.txt")
		// .withFileFromClasspath("Dockerfile", "mappable-dockerfile/Dockerfile")));


		new GenericContainer(
        new ImageFromDockerfile()
                .withDockerfileFromBuilder(builder ->
                        builder
                                .from("alpine:3.14")
                                .run("apk add --update  nginx")
                                .cmd("nginx", "-g", "daemon off;")
                                .build()))
                .withExposedPorts(80);

	}

	static  class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext>{

		@Override
		public void initialize(ConfigurableApplicationContext applicationContext) {
			// TODO Auto-generated method stub

			TestPropertyValues.of(
				"spring.datasource.url="+mysqlContainer.getJdbcUrl(),
				"spring.datasource.username"+mysqlContainer.getUsername(),
				"spring.datasource.password="+mysqlContainer.getPassword(),
				mysqlContainer.getHost(),
				mysqlContainer.getMappedPort(3306),
				mysqlContainer.getDatabaseName()
			).applyTo(applicationContext.getEnvironment());
			
		}
		
	}

        // GenericContainer<?> keycloak= new GenericContainer<>(DockerImageName.parse("keycloak:10"))
		// .waitingFor(Wait.forHttp("/auth"))
		// .withExposedPorts(8080)
		// .withEnv(Map.of(
		// 	"KEYCLOAK_USER", "testcontainers",
		// 	"KEYCLOAK_PASSWORD", "testcontainers",
		// 	"DB_VENDOR", "h2"
		// ))
		// .withCommand("/bin/sh","-c","while true; do echo ");
		

		// rabbitMQContainer=new RabbitMQContainer("rabbitmq:management")
		// .withAdminPassword("guest")
		// .withAccessToHost(true)
		// .withClasspathResourceMapping("/myData", "/root/myData",BindMode.READ_ONLY)
		// .withCommand(" sh ")
		// ;

		// rabbitMQContainer.start();
		

	
	

	

	@Test
	void getEmpTest(){
     List<Employee> empList= employeeDAO.getEmployees();
	 assertThat(empList).hasSize(18);
	}

}
