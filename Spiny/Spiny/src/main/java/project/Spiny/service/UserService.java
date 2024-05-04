package project.Spiny.service;



import org.springframework.security.core.userdetails.UserDetailsService;
import project.Spiny.entity.User;
import project.Spiny.user.WebUser;

public interface UserService extends UserDetailsService {

	public User findByUserName(String userName);

	void save(WebUser webUser);

}
