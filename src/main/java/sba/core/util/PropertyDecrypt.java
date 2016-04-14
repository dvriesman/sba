package sba.core.util;

import java.util.Map.Entry;
import java.util.Properties;

import javax.annotation.Resource;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.properties.PropertyValueEncryptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component("PropertyDecrypt")
public class PropertyDecrypt {
	
	@Autowired
	ApplicationContext applicationContext;
    
	@Autowired
    StandardPBEStringEncryptor encryptor;
    
    @Resource(name = "mapper")
    private Properties properties;

    public String decrypt(String propertyName) {
    	String result = "";
        if (properties != null) {
            for (Entry<Object, Object> e : properties.entrySet()) {
                Object oKey = e.getKey();
                if (oKey instanceof String) {
                    String key = (String)oKey;
                    if (key.equals(propertyName)) {
                    	Object value = e.getValue();
                    	if (value instanceof String) {
            				if (PropertyValueEncryptionUtils.isEncryptedValue((String)value)) {
            					value = PropertyValueEncryptionUtils.decrypt((String)value, encryptor);
            				}
                    	}
                    	result = (String) value;
                    }
                }
            }
        }
        return result;
    }
    

}
