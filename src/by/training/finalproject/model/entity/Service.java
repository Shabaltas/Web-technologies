package by.training.finalproject.model.entity;

import by.training.finalproject.model.Entity;
//import lombok.*;

import java.math.BigDecimal;

/*@Getter@Setter
@EqualsAndHashCode(callSuper=false)
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor*/
public class Service extends Entity {
    private long idSpecialization;
    private String serviceName;
    private BigDecimal cost;
    private int duration;

    public Service() {}

    public Service(int id, long idSpecialization, String serviceName, BigDecimal cost, int duration) {
        super(id);
        this.idSpecialization = idSpecialization;
        this.serviceName = serviceName;
        this.cost = cost;
        this.duration = duration;
    }

    public long getIdSpecialization() {
        return idSpecialization;
    }


    public void setIdSpecialization(long idSpecialization) {
        this.idSpecialization = idSpecialization;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
