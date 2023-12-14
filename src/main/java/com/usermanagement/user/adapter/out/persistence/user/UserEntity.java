package com.usermanagement.user.adapter.out.persistence.user;


import com.usermanagement.user.adapter.out.persistence.phonenumber.PhoneNumberEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "users")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String email;
    private String password;

    @CreationTimestamp
    @Column(updatable = false, name = "created")
    private LocalDateTime created;

    @CreationTimestamp
    @Column(name = "modified")
    private LocalDateTime modified;

    @CreationTimestamp
    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    private String token;

    @Column(name = "isactive")
    private Boolean isActive;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_phone",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "phone_id")
    )
    private List<PhoneNumberEntity> phones;
}
