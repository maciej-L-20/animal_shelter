package bada_shelter.SpringApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityService {
    @Autowired
    AuthorityRepository authorityRepository;
    @Autowired
    UserRepository userRepository;

    public void assignCurrentAuthorties(List<User> users) {
        for (User user : users) {
            Authority authority = authorityRepository.findAuthorityByUser(user);
            user.setCurrentAuthority(authority.getAuthorityName());
        }
    }
    public void assignCurrentAuthority(User user){
        Authority authority = authorityRepository.findAuthorityByUser(user);
        user.setCurrentAuthority(authority.getAuthorityName());
    }
}
