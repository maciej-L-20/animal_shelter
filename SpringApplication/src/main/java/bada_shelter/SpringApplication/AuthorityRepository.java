package bada_shelter.SpringApplication;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorityRepository extends JpaRepository<Authority,String> {
    List<Authority> findAuthoritiesByAuthorityName(String authorityName);
   Authority findAuthorityByUser(User user);
}
