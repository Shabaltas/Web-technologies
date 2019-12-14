package by.training.finalproject.entity;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class Person extends Entity{
    private String surname;
    private String name;
    private String email;
    private String phone;
    private String passwordHash;
    private Role role;
}
