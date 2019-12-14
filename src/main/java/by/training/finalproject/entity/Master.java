package by.training.finalproject.entity;

import lombok.*;

import java.sql.Date;

@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class Master extends Person {
    private Integer idPortfolio;
    private int experience;
    private Date employmentDate;
    private MasterStatus status;
}
