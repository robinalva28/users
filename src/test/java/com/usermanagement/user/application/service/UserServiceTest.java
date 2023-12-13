package com.usermanagement.user.application.service;

import com.usermanagement.user.application.port.out.UserPort;
import com.usermanagement.user.domain.PhoneNumber;
import com.usermanagement.user.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceTest {

    @MockBean
    JwtService jwtService;

    @MockBean
    UserPort userPort;

    @MockBean
    PasswordEncoder passwordEncoder;

    @Autowired
    UserService service;

    @Test
    void create() {

        // Given
        var command = new UserService.CreateUserCommand(
                "name",
                "email@domain.com",
                "password1.",
                new ArrayList<>(List.of(new PhoneNumber("number", "city", "country")))
        );
        var userExpected = new User(
                null,
                command.getName(),
                command.getEmail(),
                command.getPassword(),
                null,
                null,
                null,
                "token",
                true,
                command.getPhones()
        );


        // When
        when(userPort.existsByEmail(command.getEmail())).thenReturn(false);

        when(jwtService.generateToken(any(), any())).thenReturn("token");

        when(passwordEncoder.encode(command.getPassword())).thenReturn("passwordEncoded");

        when(userPort.createUser(anyString(), anyString(), anyString(), anyList(), anyString())).thenReturn(userExpected);

        var userResult = service.create(command);
        // Then

        Assertions.assertEquals(userExpected.getName(), userResult.getName());
        Assertions.assertEquals(userExpected.getEmail(), userResult.getEmail());
        Assertions.assertEquals(userExpected.getPassword(), userResult.getPassword());
        Assertions.assertEquals(userExpected.getPhones().get(0), userResult.getPhones().get(0));
        Assertions.assertEquals(userExpected.isActive(), userResult.isActive());
    }

    @Test
    void createEmailAlreadyInUse() {

        // Given
        var command = new UserService.CreateUserCommand(
                "name",
                "email@inuse.com",
                "password1.",
                new ArrayList<>(List.of(new PhoneNumber("number", "city", "country")))
        );

        // When
        when(userPort.existsByEmail(command.getEmail())).thenReturn(true);

        // Then
        Assertions.assertThrows(
                com.usermanagement.user.common.exceptions.BusinessException.class,
                () -> service.create(command)
        );

    }


}