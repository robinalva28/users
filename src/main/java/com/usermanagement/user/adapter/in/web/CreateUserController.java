package com.usermanagement.user.adapter.in.web;

import com.usermanagement.user.application.port.in.CreateUserUseCase;
import com.usermanagement.user.common.exceptions.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.usermanagement.user.application.port.in.CreateUserUseCase.CreateUserCommand;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Users")
public class CreateUserController {

    private final CreateUserUseCase createUserUserCase;

    @Autowired
    public CreateUserController(@Qualifier("userService") CreateUserUseCase createUserUserCase) {
        this.createUserUserCase = createUserUserCase;
    }

    @PostMapping("/users")
    @Operation(
            summary = "Create a new user",
            description = "Create a new user",
            responses = {
                    @ApiResponse(responseCode = "201", description = "User created",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = UserView.class))),
                    @ApiResponse(responseCode = "400", description = "Bad request",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "409", description = "Conflict",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class)))
            })
    public UserView create(@RequestBody CreateUserBody createUserBody) {
        CreateUserCommand createUserCommand = new CreateUserCommand(
                createUserBody.getName(),
                createUserBody.getEmail(),
                createUserBody.getPassword(),
                createUserBody.getPhones()
        );

        var result = createUserUserCase.create(createUserCommand);

        var view = new UserView();
        view.setId(String.valueOf(result.getId()));
        view.setName(result.getName());
        view.setEmail(result.getEmail());
        view.setCreated(result.getCreated());
        view.setModified(result.getModified());
        view.setLastLogin(result.getLastLogin());
        view.setToken(result.getToken());
        view.setActive(result.isActive());
        view.setPhones(result.getPhones());

        return view;
    }
}
