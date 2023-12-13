package com.usermanagement.user.adapter.in.web;


import com.usermanagement.user.domain.PhoneNumber;
import lombok.*;

import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateUserBody {
    private String name;
    private String email;
    private String password;
    private List<PhoneNumber> phones;
}
