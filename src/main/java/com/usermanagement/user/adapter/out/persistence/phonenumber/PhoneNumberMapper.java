package com.usermanagement.user.adapter.out.persistence.phonenumber;

import com.usermanagement.user.domain.PhoneNumber;
import org.springframework.stereotype.Component;


@Component
public interface PhoneNumberMapper {
    PhoneNumberEntity domainToEntity(PhoneNumber domain);

    PhoneNumber entityToDomain(PhoneNumberEntity phones);
}
