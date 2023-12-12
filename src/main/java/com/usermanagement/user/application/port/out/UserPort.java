package com.usermanagement.user.application.port.out;

import com.usermanagement.user.domain.User;

public interface UserPort {
    User createUser(User user);
}
