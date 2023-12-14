package com.usermanagement.user.application.service;

import com.usermanagement.user.application.port.in.CreateUserUseCase;
import com.usermanagement.user.application.port.out.UserPort;
import com.usermanagement.user.common.exceptions.BadRequestException;
import com.usermanagement.user.common.exceptions.BusinessException;
import com.usermanagement.user.common.security.JwtPayload;
import com.usermanagement.user.domain.User;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserService implements CreateUserUseCase {

    private final Logger log = Logger.getLogger(UserService.class);

    private final UserPort userPort;

    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;

    @Value("${password.validation.regex}")
    private String passwordRegex;

    @Autowired
    public UserService(UserPort userPort, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.userPort = userPort;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User create(CreateUserCommand createUserCommand) {

        log.info("Service: validating password format...");
        var passwordFormatError = this.validatePasswordFormat(createUserCommand.getPassword());

        if (!passwordFormatError.isEmpty()) {
            throw new BadRequestException(passwordFormatError);
        }

        log.info("Service: checking if email is already in use...");
        if (userPort.existsByEmail(createUserCommand.getEmail())) {
            var message = "Email ".concat(createUserCommand.getEmail().concat(" is already in use"));
            throw new BusinessException(message);
        }

        log.info("Service: generating access token...");
        Map<String, Object> extraClaims = Map.of("name", createUserCommand.getName(), "isActive", Boolean.TRUE);
        var token = jwtService.generateToken(extraClaims, new JwtPayload(createUserCommand.getName(), createUserCommand.getEmail(), Boolean.TRUE));

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


    private String validatePasswordFormat(String password) {
        if (!password.matches(passwordRegex)) {
            return "Password format is invalid, it must have at least 1 uppercase, 1 lowercase, 1 number and 1 special character";
        }
        return "";
    }
}
