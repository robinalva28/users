package com.usermanagement.user.adapter.out.persistence.user;

import com.usermanagement.user.domain.User;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper {

    UserEntity domainToEntity(User domain);

    User entityToDomain(UserEntity entity);
}
