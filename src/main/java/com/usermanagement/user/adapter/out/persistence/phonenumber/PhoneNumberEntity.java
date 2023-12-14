package com.usermanagement.user.adapter.out.persistence.phonenumber;


import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "phone_numbers")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PhoneNumberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String number;
    private String cityCode;
    private String countryCode;

}
