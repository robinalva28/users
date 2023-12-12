package com.usermanagement.user.application.service;

import com.usermanagement.user.adapter.web.in.UserView;
import com.usermanagement.user.application.port.in.CreateUserUseCase;
import org.springframework.stereotype.Component;

@Component
public class UserService implements CreateUserUseCase {


    @Override
    public UserView create(CreateUserCommand createUserCommand) {
        return new UserView(
                createUserCommand.getName(),
                createUserCommand.getEmail(),
                createUserCommand.getPassword(),
                createUserCommand.getPhones(),
                null,
                null,
                null,
                null,
                null,
                false
        );
    }
}
