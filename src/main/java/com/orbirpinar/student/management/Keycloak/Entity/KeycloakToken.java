package com.orbirpinar.student.management.Keycloak.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;


import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("KeycloakToken")
public class KeycloakToken implements Serializable {

    @Id
    private String id;

    @Indexed
    @Column(length = 10485760)
    private String access_token;


    private int expires_in;

    private int refresh_expires_in;

    @Indexed
    @Column(length = 10485760)
    private String refresh_token;
}