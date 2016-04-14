package sba.core.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import sba.core.dbms.data.CatalogInfo;
import sba.core.dbms.repository.CatalogRepository;

@Component
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {
	
  @Autowired
  AppServerEnv appServerEnv;
  
  @Autowired
  CatalogRepository catalogRepository;
  
  @Override
  public void onApplicationEvent(final ContextRefreshedEvent event) {
	  updateAdminPasswordWithinDatabaseFromPropertiesFile();
  }
  
  private void updateAdminPasswordWithinDatabaseFromPropertiesFile() {
	  CatalogInfo catalogInfo = catalogRepository.findByAccessName("admin");
	  if (catalogInfo == null) {
		  catalogInfo = new CatalogInfo();
		  catalogInfo.setAccessName("admin");
		  catalogInfo.setPassword(appServerEnv.getAdminPassword());
		  catalogInfo.setPath("");
		  catalogInfo.setCatalogType(null);
	  } else {
		  catalogInfo.setPassword(appServerEnv.getAdminPassword());
	  }
	  catalogRepository.save(catalogInfo);
  }
  
 
}
