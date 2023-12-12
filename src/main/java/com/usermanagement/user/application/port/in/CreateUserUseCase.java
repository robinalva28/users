package com.usermanagement.user.application.port.in;

import com.usermanagement.user.adapter.web.in.UserView;
import com.usermanagement.user.domain.PhoneNumber;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CreateUserUseCase {

    UserView create(CreateUserCommand createUserCommand);

    @Getter
    @Setter
    @NoArgsConstructor
    class CreateUserCommand {
        private String name;
        private String email;
        private String password;
        private List<PhoneNumber> phones;

        public CreateUserCommand(String name, String email, String password, List<PhoneNumber> phones) {
            this.name = name;
            this.email = email;
            this.password = password;
            this.phones = phones;
        }
    }

}
