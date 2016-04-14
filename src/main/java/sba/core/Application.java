package sba.core;

import java.lang.management.ManagementFactory;

import org.apache.log4j.Logger;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {
	
	public static String mode = null;
	
	private static Logger log = Logger.getLogger(Application.class);
	
	public static ApplicationContext applicationContext = null;
	
    public static void main(String[] args) {
    	
    	 mode = args != null && args.length > 0 ? args[0] : null;

         if (log.isDebugEnabled()) {
        	 log.debug("PID:" + ManagementFactory.getRuntimeMXBean().getName() + " Application mode:" + mode + " context:" + applicationContext);
         }
         
         if (applicationContext != null && mode != null && "stop".equals(mode)) {
             System.exit(SpringApplication.exit(applicationContext, new ExitCodeGenerator() {
                 @Override
                 public int getExitCode() {
                     return 0;
                 }
             }));
         }
         else {
        	 
             SpringApplication app = new SpringApplication(Application.class);
             
             applicationContext = app.run(args);
             
             if (log.isDebugEnabled()) {
            	 log.debug("PID:" + ManagementFactory.getRuntimeMXBean().getName() + " Application started context:" + applicationContext);
             }
         }
    }
}
