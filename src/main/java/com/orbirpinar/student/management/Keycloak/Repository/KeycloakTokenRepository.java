package com.orbirpinar.student.management.Keycloak.Repository;



import com.orbirpinar.student.management.Keycloak.Entity.KeycloakToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeycloakTokenRepository extends CrudRepository<KeycloakToken,String> {
}
