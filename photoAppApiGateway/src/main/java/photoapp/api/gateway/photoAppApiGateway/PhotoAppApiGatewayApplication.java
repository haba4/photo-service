package photoapp.api.gateway.photoAppApiGateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.EnableZuulServer;

@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
public class PhotoAppApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhotoAppApiGatewayApplication.class, args);
	}

}
