package com.epitech.bankserver.model.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email; //format a@a.com
    private String password;

}
