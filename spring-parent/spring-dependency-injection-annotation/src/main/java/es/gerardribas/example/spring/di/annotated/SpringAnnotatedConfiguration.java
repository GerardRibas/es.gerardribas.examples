package es.gerardribas.example.spring.di.annotated;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import es.gerardribas.example.spring.di.beans.MyBean;
import es.gerardribas.example.spring.di.beans.impl.MyBeanImpl;

/**
 * @author Gerard Ribas Canals (gerard.ribas.canals@gmail.com)
 *
 */
@Configuration
public class SpringAnnotatedConfiguration {
	
	@Bean
	public MyBean getMyBean(){
		return new MyBeanImpl();
	}
	
}
