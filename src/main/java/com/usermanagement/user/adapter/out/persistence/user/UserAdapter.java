package com.usermanagement.user.adapter.out.persistence.user;

import com.sun.jdi.InternalException;
import com.usermanagement.user.adapter.out.persistence.phonenumber.PhoneNumberMapper;
import com.usermanagement.user.application.port.out.UserPort;
import com.usermanagement.user.domain.PhoneNumber;
import com.usermanagement.user.domain.User;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserAdapter implements UserPort {

    private final Logger log = Logger.getLogger(UserAdapter.class);

    private final UserRepository userRepository;

    private final PhoneNumberMapper phoneNumberMapper;

    private final UserMapper userMapper;

    @Autowired
    UserAdapter(UserRepository userRepository, PhoneNumberMapper phoneNumberMapper, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.phoneNumberMapper = phoneNumberMapper;
        this.userMapper = userMapper;
    }

    @Override
    public User createUser(String name, String email, String password, List<PhoneNumber> phoneNumbers, String token) {

        UserEntity userEntity = new UserEntity();
        UserEntity userResult;

        userEntity.setName(name);
        userEntity.setEmail(email);
        userEntity.setPassword(password);
        userEntity.setPhones(phoneNumbers.stream().map(phoneNumberMapper::domainToEntity).collect(Collectors.toList()));
        userEntity.setCreated(LocalDateTime.now());
        userEntity.setModified(LocalDateTime.now());
        userEntity.setLastLogin(LocalDateTime.now());
        userEntity.setToken(token);
        userEntity.setIsActive(true);

        log.info("Adapter: saving userEntity...");
        try {
            userResult = userRepository.save(userEntity);
        } catch (Exception e) {
            log.error("Adapter: error saving userEntity: " + e.getMessage());
            throw new InternalException("Internal server error");
        }
        log.info("Adapter: userEntity saved successfully");

        return userMapper.entityToDomain(userResult);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsUserEntityByEmail(email);
    }
}
