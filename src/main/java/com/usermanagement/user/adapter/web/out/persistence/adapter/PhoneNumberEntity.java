package com.usermanagement.user.adapter.web.out.persistence.adapter;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "phone_number")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PhoneNumberEntity {

    @Id
    private Long id;
    private String number;
    private String cityCode;
    private String countryCode;

}
