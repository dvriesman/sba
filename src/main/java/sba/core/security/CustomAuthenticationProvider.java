package sba.core.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	private final String LDAP =  "ldap://0.0.0.0:000";
	private final String LDAP_DN =  "dc=teste,dc=com,dc=br";
	
	@Autowired
	CustomUserDetailsService customUserDetailsService;
 
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    	
    	Authentication auth = null;
    	try {
            String name = authentication.getName();
            String password = authentication.getCredentials().toString();
            List<GrantedAuthority> grantedAuths = new ArrayList<>();
            	
        	LdapAuthentication adAuthenticator = new LdapAuthentication("NT", LDAP, LDAP_DN);
        	
    		Map<String,Object> attrs = adAuthenticator.authenticate(name, password);
    		if (attrs != null) {
            	UserDetails user = customUserDetailsService.loadUserByUsername(name);
            	grantedAuths.addAll(user.getAuthorities());
            	auth = new UsernamePasswordAuthenticationToken(user, password, grantedAuths);
    		}
    	} catch (Exception e) {
  			e.printStackTrace();
    	}
        return auth;
    }
 
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
    
}