package sba.core.app;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * Factory dos Beans da aplicação
 * @author Denny Richard San Vriesman
 * @since 06/2015
 *
 */
@Configuration
public class AppConfig {
	
	@Bean
	public StandardPBEStringEncryptor standardPBEStringEncryptor() {
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
    	encryptor.setPassword("AnotherKey");
    	return encryptor;
	}
	
    @Bean(name = "mapper")
    public PropertiesFactoryBean mapper() {
        PropertiesFactoryBean bean = new PropertiesFactoryBean();
        bean.setLocation(new ClassPathResource("application.properties"));
        return bean;
    }	
     

}
