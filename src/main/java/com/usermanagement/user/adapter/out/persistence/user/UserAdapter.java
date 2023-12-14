package com.usermanagement.user.adapter.out.persistence.user;

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

    @Autowired
    UserAdapter(UserRepository userRepository, PhoneNumberMapper phoneNumberMapper) {
        this.userRepository = userRepository;
        this.phoneNumberMapper = phoneNumberMapper;
    }

    @Override
    public User createUser(String name, String email, String password, List<PhoneNumber> phoneNumbers, String token) {

        UserEntity userEntity = new UserEntity();

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
            userRepository.save(userEntity);
        } catch (Exception e) {
            log.error("Adapter: error saving userEntity: " + e.getMessage());
            throw e;
        }

        return new User(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.getCreated(),
                userEntity.getModified(),
                userEntity.getLastLogin(),
                userEntity.getToken(),
                userEntity.getIsActive(),
                userEntity.getPhones().stream().map(phoneNumberMapper::entityToDomain).collect(Collectors.toList())
        );
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsUserEntityByEmail(email);
    }
}
