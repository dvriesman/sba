package sba.core.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import sba.core.dbms.data.CatalogInfo;
import sba.core.dbms.repository.CatalogRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private CatalogRepository catalogRepository;

	@Override
	public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {
		
		CatalogInfo catalogInfo = catalogRepository.findByAccessName(loginName);
		
		if (catalogInfo == null) {
			throw new UsernameNotFoundException(String.format("User %s not found!", loginName));
		}

		return new AppUserDetail(catalogInfo);
	}
	
	public final static class AppUserDetail extends CatalogInfo implements UserDetails {

		private static final long serialVersionUID = 1L;

		public AppUserDetail(CatalogInfo catalogInfo) {
			this.setId(catalogInfo.getId());
			this.setPassword(catalogInfo.getPassword());
			this.setAccessName(catalogInfo.getAccessName());
		}

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			RuleInfo rule = new RuleInfo("USER");
			Set<RuleInfo> rules = new HashSet<>();
			rules.add(rule);
			return rules;
		}

		@Override
		public String getPassword() {
			return super.getPassword();
		}

		@Override
		public boolean isAccountNonExpired() {
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			return true;
		}

		@Override
		public boolean isEnabled() {
			return true;
		}

		@Override
		public String getUsername() {
			return super.getAccessName();
		}
		
	};
	

}
