package com.usermanagement.user.adapter.out.persistence.phonenumber;

import com.usermanagement.user.domain.PhoneNumber;
import org.springframework.stereotype.Component;

@Component
public class PhoneNumberMapperImpl implements PhoneNumberMapper{
    @Override
    public PhoneNumberEntity domainToEntity(PhoneNumber domain) {
        return new PhoneNumberEntity(null, domain.getNumber(), domain.getCityCode(), domain.getCountryCode());
    }

    @Override
    public PhoneNumber entityToDomain(PhoneNumberEntity phones) {
        return new PhoneNumber(phones.getNumber(), phones.getCityCode(), phones.getCountryCode());
    }
}
