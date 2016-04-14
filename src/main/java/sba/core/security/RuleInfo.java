package sba.core.security;

import org.springframework.security.core.GrantedAuthority;

public class RuleInfo implements GrantedAuthority {

	private static final long serialVersionUID = 1L;
	
	private String name;
	
	public RuleInfo(String name) {
		this.name = name;
	}

	@Override
	public String getAuthority() {
		return name;
	}

}
