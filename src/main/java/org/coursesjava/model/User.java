package org.coursesjava.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    private int id;
    private String name;
    private String nickname;
    private String birthday;
    private String password;
    private Account account;
}
