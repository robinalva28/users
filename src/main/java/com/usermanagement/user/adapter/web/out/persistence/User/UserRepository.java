package com.usermanagement.user.adapter.web.out.persistence.User;

import com.usermanagement.user.adapter.web.out.persistence.adapter.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
