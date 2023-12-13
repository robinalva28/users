package com.usermanagement.user.application.service;

import com.usermanagement.user.application.port.in.CreateUserUseCase;
import com.usermanagement.user.application.port.out.UserPort;
import com.usermanagement.user.common.exceptions.BusinessException;
import com.usermanagement.user.common.security.JwtPayload;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.usermanagement.user.domain.User;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserService implements CreateUserUseCase {

    private final Logger log = Logger.getLogger(UserService.class);

    private final UserPort userPort;

    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserPort userPort, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.userPort = userPort;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User create(CreateUserCommand createUserCommand) {

        log.info("Service: checking if email is already in use...");
        if (userPort.existsByEmail(createUserCommand.getEmail())) {
            var message = "Email ".concat(createUserCommand.getEmail().concat(" is already in use"));
            throw new BusinessException(message);
        }

        log.info("Service: generating access token...");
        Map<String,Object> extraClaims = Map.of("name", createUserCommand.getName(), "isActive", Boolean.TRUE);
        var token = jwtService.generateToken(extraClaims ,new JwtPayload(createUserCommand.getName(), createUserCommand.getEmail(), Boolean.TRUE));

        var password = passwordEncoder.encode(createUserCommand.getPassword());

        log.info("Service: call UserPort's method createUser()");
        return userPort.createUser(
                createUserCommand.getName(),
                createUserCommand.getEmail(),
                password,
                createUserCommand.getPhones(),
                token
        );
    }
}
