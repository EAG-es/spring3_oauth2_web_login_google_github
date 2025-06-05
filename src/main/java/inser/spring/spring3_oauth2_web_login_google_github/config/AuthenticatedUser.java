package inser.spring.spring3_oauth2_web_login_google_github.config;

import inser.spring.spring3_oauth2_web_login_google_github.entities.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * Wrapper class to hold Authenticated User Details and Entity instance
 */
public class AuthenticatedUser extends User {

	private static final long serialVersionUID = 1L;
	private Users user;
	
	public AuthenticatedUser(Users user, Collection<? extends GrantedAuthority> authorities) {
		super(user.getIdUser(), user.getPassword(), authorities);
		this.user = user;
	}
	
	public AuthenticatedUser(Users user, boolean enabled, boolean accountNonExpired,
                             boolean credentialsNonExpired, boolean accountNonLocked,
                             Collection<? extends GrantedAuthority> authorities) {
		super(user.getIdUser(), user.getPassword(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.user = user;
	}

	public Users getUser() {
		return user;
	}

}
