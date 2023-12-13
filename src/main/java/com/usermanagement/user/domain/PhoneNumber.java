package com.usermanagement.user.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhoneNumber {
    private String number;
    private String cityCode;
    private String countryCode;
}
