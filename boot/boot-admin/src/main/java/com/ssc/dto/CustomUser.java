package com.ssc.dto;

import java.util.Collection;
import java.util.UUID;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

@Getter
public class CustomUser extends User {

    private final UUID id;

    public CustomUser(UUID id,
        String userName,
        String password,
        Collection<GrantedAuthority> authorities
        ) {
        super(userName, password, authorities);
        this.id = id;
    }

}
