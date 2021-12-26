package com.orbirpinar.student.management.Keycloak.Integration;

import com.orbirpinar.student.management.Api.User.DTO.UserViewDto;
import com.orbirpinar.student.management.Api.User.Entity.User;
import com.orbirpinar.student.management.Api.User.Repository.UserRepository;
import com.orbirpinar.student.management.Keycloak.Service.KeycloakUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static com.orbirpinar.student.management.Keycloak.Integration.KeycloakEvent.*;

@Service
@Slf4j
public class KeycloakConsumerService {


    private final UserRepository userRepository;

    private final KeycloakUserService keycloakUserService;

    @Autowired
    public KeycloakConsumerService(UserRepository userRepository, KeycloakUserService keycloakUserService) {
        this.userRepository = userRepository;
        this.keycloakUserService = keycloakUserService;
    }

    @KafkaListener(topics = REGISTER,groupId = "${kafka.group-id}")
    public void listenRegisterEvent(ConsumerRecord<?, ?> data) throws IOException {
        String username = data.value().toString();
        log.info("<REGISTER EVENT LISTENER CALLED> FOR {}",username);
        if(!userRepository.existsByUsername(username)) {
            saveKeycloakUserToDb(username);
            log.info("<NEW USER HAS BEEN CREATED> USERNAME: {}",username);
        }
    }


    @KafkaListener(topics = LOGIN,groupId = "${kafka.group-id}")
    public void listenLoginEvent(ConsumerRecord<?, ?> data) throws IOException {
        String username = data.value().toString();
        log.info("<LOGIN EVENT LISTENER CALLED> FOR {}",username);
        if(!userRepository.existsByUsername(username)) {
            saveKeycloakUserToDb(username);
            log.info("<NEW USER HAS BEEN CREATED> USERNAME: {}",username);
        }
    }

    private void saveKeycloakUserToDb(String username) throws IOException {
        List<UserViewDto> listOfUser = keycloakUserService.getByUsername(username);
        UserViewDto keycloakUser = listOfUser.get(0);
        User user = new User();
        user.setId(keycloakUser.getId());
        user.setEmail(keycloakUser.getEmail());
        user.setFirstName(keycloakUser.getFirstName());
        user.setEnabled(keycloakUser.getEnabled());
        user.setLastName(keycloakUser.getLastName());
        user.setEmailVerified(keycloakUser.getEmailVerified());
        user.setUsername(keycloakUser.getUsername());
        userRepository.save(user);
    }
}
