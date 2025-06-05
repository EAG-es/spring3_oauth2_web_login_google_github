package inser.spring.spring3_oauth2_web_login_google_github.services;

import inser.spring.spring3_oauth2_web_login_google_github.config.AuthenticatedUser;
import inser.spring.spring3_oauth2_web_login_google_github.entities.Users;
import inser.spring.spring3_oauth2_web_login_google_github.repositories.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Login utility service to create and retrieve authenticated user instance 
 */
@Service
public class LoginUtilityService {

	@Autowired
	UsersRepository usersRepository;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 *
	 * @param username
	 * @return
	 */
	public AuthenticatedUser findMatch(String username) {
		List<Users> users = usersRepository.findByIdUser(username);
		if (users == null || users.size() == 0) {
			logger.info("User not found: " + username);
			throw new UsernameNotFoundException("UserName " + username + " doesn't exists");
		}
		Users user = users.get(0);
        logger.info("User Instance: {}", user);
		// For multiple content level access, use content-level permissions for each role
		// Mapping: USER entity - (1-n) Role entity - (1-n) Permission entity
		String [] roles_string = user.getRole().split(",");
		GrantedAuthority authority;
		List<GrantedAuthority> authorities_list = new ArrayList<>();
		for (var role: roles_string) {
			authority = new SimpleGrantedAuthority(role.trim());
			authorities_list.add(authority);
		}
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>(authorities_list);
		return new AuthenticatedUser(user, authorities);
	}

	/**
	 * Get currently logged-in user's entity instance (for business logic)
	 * @return
	 */
	public @Nullable Users getLoggedInUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || authentication.getPrincipal() == null) {
			return null;
		}
		return ((AuthenticatedUser) authentication.getPrincipal()).getUser();
	}
}
