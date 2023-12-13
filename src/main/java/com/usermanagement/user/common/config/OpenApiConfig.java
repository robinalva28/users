package com.usermanagement.user.common.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "User Management API",
                version = "1.0.0",
                description = "User Management API",
                summary = "This User Management API, will create new users and return a token to be used in the future to authenticate the user."
        )
)

public class OpenApiConfig {
}
