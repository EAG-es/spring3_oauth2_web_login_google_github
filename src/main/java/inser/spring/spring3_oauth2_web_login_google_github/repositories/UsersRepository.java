package inser.spring.spring3_oauth2_web_login_google_github.repositories;

import inser.spring.spring3_oauth2_web_login_google_github.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users, Long> {
	
	List<Users> findByIdUser(String id_user);

}
