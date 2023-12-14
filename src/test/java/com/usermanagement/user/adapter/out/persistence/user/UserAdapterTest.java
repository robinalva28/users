package com.usermanagement.user.adapter.out.persistence.user;

import com.sun.jdi.InternalException;
import com.usermanagement.user.adapter.out.persistence.phonenumber.PhoneNumberMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserAdapterTest {

    @Autowired
    private UserAdapter userAdapter;

    @Autowired
    private PhoneNumberMapper phoneNumberMapper;

    @MockBean
    private UserRepository userRepositoryMock;


    @Test
    void createUser() {
        // Given

        var userEntityMock = new UserEntity();
        userEntityMock.setName("name");
        userEntityMock.setEmail("email");
        userEntityMock.setPassword("password");
        userEntityMock.setPhones(new ArrayList<>(List.of(phoneNumberMapper.domainToEntity(new com.usermanagement.user.domain.PhoneNumber("number", "cityCode", "countryCode")))));
        userEntityMock.setCreated(LocalDateTime.now());
        userEntityMock.setModified(LocalDateTime.now());
        userEntityMock.setLastLogin(LocalDateTime.now());
        userEntityMock.setToken("token");
        userEntityMock.setIsActive(true);


        // When

        when(userRepositoryMock.save(Mockito.any(UserEntity.class))).thenReturn(userEntityMock);

        var user = userAdapter.createUser("name", "email", "password", new ArrayList<>(List.of(new com.usermanagement.user.domain.PhoneNumber("number", "cityCode", "countryCode"))), "token");

        // Then
        Assertions.assertEquals(user.getName(), userEntityMock.getName());
        Assertions.assertEquals(user.getEmail(), userEntityMock.getEmail());


    }

    @Test
    void doesThrowInternalException() {

        // Given

        // When
        when(userRepositoryMock.save(Mockito.any(UserEntity.class))).thenThrow(new InternalException());

        var exception = assertThrows(InternalException.class, () ->
                userAdapter.createUser("name", "email", "password", new ArrayList<>(List.of(new com.usermanagement.user.domain.PhoneNumber("number", "cityCode", "countryCode"))), "token"));

        // Then
        Assertions.assertEquals("Internal server error", exception.getMessage());
    }

    @Test
    void existsByEmail() {

        // Given
        // When
        when(userRepositoryMock.existsUserEntityByEmail(Mockito.anyString())).thenReturn(true);

        var result = userAdapter.existsByEmail("email");

        // Then
        Assertions.assertTrue(result);

    }
}