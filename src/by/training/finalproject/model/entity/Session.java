package by.training.finalproject.model.entity;

import by.training.finalproject.model.Entity;
//import lombok.*;

import java.sql.Time;
import java.sql.Date;
import java.util.Objects;

/*@Getter@Setter
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)*/
public class Session extends Entity {
    private long idClient;
    private long idMaster;
    private long idService;
    private Date date;
    private Time time;
    public Session() { }

    public Session(int id, long idClient, long idMaster, long idService, Date date, Time time) {
        super(id);
        this.idClient = idClient;
        this.idMaster = idMaster;
        this.idService = idService;
        this.date = date;
        this.time = time;
    }

    public long getIdClient() {
        return idClient;
    }

    public void setIdClient(long idClient) {
        this.idClient = idClient;
    }

    public long getIdMaster() {
        return idMaster;
    }

    public void setIdMaster(long idMaster) {
        this.idMaster = idMaster;
    }

    public long getIdService() {
        return idService;
    }

    public void setIdService(long idService) {
        this.idService = idService;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Session{" +
                "idClient=" + idClient +
                ", idMaster=" + idMaster +
                ", idService=" + idService +
                ", date=" + date +
                ", time=" + time +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return idClient == session.idClient &&
                idMaster == session.idMaster &&
                idService == session.idService &&
                Objects.equals(date, session.date) &&
                Objects.equals(time, session.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idClient, idMaster, idService, date, time);
    }
}
