package com.orbirpinar.student.management.Keycloak.Service;


import com.orbirpinar.student.management.Keycloak.Entity.KeycloakToken;
import com.orbirpinar.student.management.Keycloak.Repository.KeycloakTokenRepository;
import com.orbirpinar.student.management.Utils.NullAwareBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class KeycloakTokenService {

    @Autowired
    private final KeycloakTokenRepository keycloakTokenRepository;

    public KeycloakTokenService(KeycloakTokenRepository keycloakTokenRepository) {
        this.keycloakTokenRepository = keycloakTokenRepository;
    }

    public void save(KeycloakToken keycloakToken) {
        keycloakToken.setId("keycloak");
        keycloakTokenRepository.save(keycloakToken);
    }

    public Optional<KeycloakToken> getById(String id) {
        return keycloakTokenRepository.findById(id);
    }

    public KeycloakToken saveOrUpdate(KeycloakToken newKeycloakToken) {
        Optional<KeycloakToken> keycloakToken = this.getById("keycloak");
        if(keycloakToken.isPresent()) {
            NullAwareBeanUtils.copyNonNullProperties(newKeycloakToken,keycloakToken.get());
            keycloakToken.ifPresent(keycloakTokenRepository::save);
        }else {
            save(newKeycloakToken);
        }
        return this.getById("keycloak").orElseThrow(() -> new EntityNotFoundException(""));
    }
}