package com.usermanagement.user.adapter.out.persistence.user;

import com.usermanagement.user.adapter.out.persistence.phonenumber.PhoneNumberMapper;
import com.usermanagement.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMapperImpl implements UserMapper {

    private final PhoneNumberMapper phoneNumberMapper;

    @Autowired
    public UserMapperImpl(PhoneNumberMapper phoneNumberMapper) {
        this.phoneNumberMapper = phoneNumberMapper;
    }

    @Override
    public UserEntity domainToEntity(User domain) {
        return new UserEntity(
                domain.getId(),
                domain.getName(),
                domain.getEmail(),
                domain.getPassword(),
                domain.getCreated(),
                domain.getModified(),
                domain.getLastLogin(),
                domain.getToken(),
                domain.isActive(),
                domain.getPhones().stream().map(phoneNumberMapper::domainToEntity).collect(Collectors.toList())
        );
    }

    @Override
    public User entityToDomain(UserEntity entity) {
        return new User(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getCreated(),
                entity.getModified(),
                entity.getLastLogin(),
                entity.getToken(),
                entity.getIsActive(),
                entity.getPhones().stream().map(phoneNumberMapper::entityToDomain).collect(Collectors.toList()));
    }
}
