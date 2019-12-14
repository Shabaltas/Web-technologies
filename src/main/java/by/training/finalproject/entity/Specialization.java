package by.training.finalproject.entity;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class Specialization extends Entity{
    private String title;
}
