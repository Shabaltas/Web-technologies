package by.training.finalproject.model.entity;

//import lombok.*;

import java.sql.Date;

/*@Getter@Setter
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)*/
public class Master extends Person {
    private Integer idPortfolio;
    private int experience;
    private Date employmentDate;
    private MasterStatus status;

    public Integer getIdPortfolio() {
        return idPortfolio;
    }

    public Master(Integer idPortfolio, int experience, Date employmentDate, MasterStatus status) {
        this.idPortfolio = idPortfolio;
        this.experience = experience;
        this.employmentDate = employmentDate;
        this.status = status;
    }
    public Master(){}

    public void setIdPortfolio(Integer idPortfolio) {
        this.idPortfolio = idPortfolio;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public Date getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(Date employmentDate) {
        this.employmentDate = employmentDate;
    }

    public MasterStatus getStatus() {
        return status;
    }

    public void setStatus(MasterStatus status) {
        this.status = status;
    }
}
