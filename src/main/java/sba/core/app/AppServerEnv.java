package sba.core.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppServerEnv {
	
	public static final String VERSION = "0.0.1";
	
	@Value("${sba.admin.password}")
	private String adminPassword;

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	
}
