/*
Author: Ene Bogdan
Country: Romania
*/
package config;


import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class ConnectionAttributes {
    private final String URL = "jdbc:mysql://localhost/exercises";
    private final String USER = "root";
    private final String PASSWORD = "";
}
