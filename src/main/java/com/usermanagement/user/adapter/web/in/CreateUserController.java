package com.usermanagement.user.adapter.web.in;

import com.usermanagement.user.application.port.in.CreateUserUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.usermanagement.user.application.port.in.CreateUserUseCase.CreateUserCommand;

@RestController
@RequestMapping("/api/v1")
public class CreateUserController {

    private final CreateUserUseCase createUserUserCase;

    @Autowired
    public CreateUserController(@Qualifier("userService") CreateUserUseCase createUserUserCase) {
        this.createUserUserCase = createUserUserCase;
    }

    //TODO: add swagger documentation
    @PostMapping("/users")
    public UserView create(@RequestBody CreateUserBody createUserBody) {
        CreateUserCommand createUserCommand = new CreateUserCommand(
                createUserBody.getName(),
                createUserBody.getEmail(),
                createUserBody.getPassword(),
                createUserBody.getPhones()
        );
        return createUserUserCase.create(createUserCommand);
    }

}
