package org.zerock.booksys.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Map;

@Getter
@Setter
@ToString
public class CustomerSecurityDTO extends User {
    private String cId;
    private String cPassword;
    private String name;
    private String phoneNumber;

    public CustomerSecurityDTO(String username, String password, String name, String phoneNumber,
                               Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.cId = username;
        this.cPassword = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
}
