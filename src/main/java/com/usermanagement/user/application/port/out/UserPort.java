package com.usermanagement.user.application.port.out;

import com.usermanagement.user.domain.PhoneNumber;
import com.usermanagement.user.domain.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserPort {
    User createUser(String name, String email, String password, List<PhoneNumber> phoneNumbers, String token);

    Boolean existsByEmail(String email);
}
