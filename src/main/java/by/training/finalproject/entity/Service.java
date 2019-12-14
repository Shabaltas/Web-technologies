package by.training.finalproject.entity;

import lombok.*;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper=false)
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Service extends Entity{
    private long idSpecialization;
    private String serviceName;
    private BigDecimal cost;
    private int duration;
}
