package com.usermanagement.user.adapter.web.in;


import com.usermanagement.user.domain.PhoneNumber;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserBody {
    private String name;
    private String email;
    private String password;
    private List<PhoneNumber> phones;
}