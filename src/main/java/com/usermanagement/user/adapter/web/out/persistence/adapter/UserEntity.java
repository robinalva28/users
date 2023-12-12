package com.usermanagement.user.adapter.web.out.persistence.adapter;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity
@Table(name = "user")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    private Long id;
    private String name;
    private String email;
    private String password;
    private String created;
    private String modified;
    private String last_login;
    private String token;

    @OneToMany(cascade = CascadeType.ALL)
    private List<PhoneNumberEntity> phones;
}
