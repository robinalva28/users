package com.usermanagement.user.application.port.in;

import com.usermanagement.user.common.validations.CommandValidation;
import com.usermanagement.user.domain.PhoneNumber;
import com.usermanagement.user.domain.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CreateUserUseCase {

    User create(CreateUserCommand createUserCommand);

    @Getter
    @Setter
    @NoArgsConstructor
    class CreateUserCommand extends CommandValidation<CreateUserCommand> {

        @NotBlank(message = "is required")
        private String name;

        @NotBlank(message = "is required")
        @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "must be a valid email")
        private String email;

        @NotBlank(message = "is required")
        private String password;

        private List<PhoneNumber> phones;

        public CreateUserCommand(String name, String email, String password, List<PhoneNumber> phones) {
            this.name = name;
            this.email = email;
            this.password = password;
            this.phones = phones;
            this.validateSelf();
        }
    }
}
