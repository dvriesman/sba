package sba.core.util;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Properties;

import javax.annotation.Resource;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.properties.PropertyValueEncryptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component("PropertyMapper")
public class PropertyMapper {

    @Autowired
    ApplicationContext applicationContext;
    
    @Autowired
    StandardPBEStringEncryptor  encryptor;
        
    @Resource(name = "mapper")
    private Properties properties;
    
    public HashMap<String, Object> startWith(String startWith) {
        return startWith(startWith, false);
    }

    public HashMap<String, Object> startWith(String startWith, boolean removeStartWith) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        if (properties != null) {
            for (Entry<Object, Object> e : properties.entrySet()) {
                Object oKey = e.getKey();
                if (oKey instanceof String) {
                    String key = (String)oKey;
                    if (((String) oKey).startsWith(startWith)) {
                        if (removeStartWith) {  
                            key = key.substring(startWith.length());
                        }
                    	Object value = e.getValue();
                    	if (value instanceof String) {
            				if (PropertyValueEncryptionUtils.isEncryptedValue((String)value)) {
            					value = PropertyValueEncryptionUtils.decrypt((String)value, encryptor);
            				}
                    	}
                    	result.put(key, value);
                    }
                }
            }
        }
        return result;
    }
    
}
