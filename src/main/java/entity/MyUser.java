package entity;/*
Author: Ene Bogdan
Country: Romania
*/

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MyUser {

    private Integer id;
    private String username;
    private String password;
    private String email;

}
