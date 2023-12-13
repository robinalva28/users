/*
package com.usermanagement.user.adapter.in.web;

import com.usermanagement.user.application.port.in.CreateUserUseCase;
import com.usermanagement.user.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CreateUserControllerTest {

    @MockBean
    CreateUserUseCase createUserUseCase;


    @Test
    void create() throws Exception {

        when(createUserUseCase.create(any())).thenReturn(new User());

        given()
                .body(new CreateUserBody("", "", "", null))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/api/v1/users")
                .then()
                .statusCode(201)
                .body("name", org.hamcrest.Matchers.equalTo(""))
                .body("email", org.hamcrest.Matchers.equalTo(""))
                .body("password", org.hamcrest.Matchers.equalTo(""))
                .body("phones", org.hamcrest.Matchers.equalTo(null))
                .body("token", org.hamcrest.Matchers.equalTo(null))
                .body("created", org.hamcrest.Matchers.equalTo(null))
                .body("modified", org.hamcrest.Matchers.equalTo(null))
                .body("lastLogin", org.hamcrest.Matchers.equalTo(null))
                .body("isActive", org.hamcrest.Matchers.equalTo(false))
                .body("id", org.hamcrest.Matchers.equalTo(null));

    }
}
*/
