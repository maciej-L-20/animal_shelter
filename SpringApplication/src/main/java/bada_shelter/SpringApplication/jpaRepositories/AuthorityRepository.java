package bada_shelter.SpringApplication.jpaRepositories;

import bada_shelter.SpringApplication.models.Authority;
import bada_shelter.SpringApplication.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorityRepository extends JpaRepository<Authority,String> {
    List<Authority> findAuthoritiesByAuthorityName(String authorityName);
   Authority findAuthorityByUser(User user);
   void deleteAuthorityByUser(User user);
}
