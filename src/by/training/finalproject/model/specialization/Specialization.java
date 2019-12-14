package by.training.finalproject.model.specialization;

import by.training.finalproject.model.Entity;
//import lombok.*;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;


@XmlRootElement(name = "specialization")
public class Specialization extends Entity {
    //@NonNull
    @XmlAttribute
    private String title;

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Specialization{" +
                "id=" + getId() +
                ", title='" + title + '\'' +
                '}';
    }

    public Specialization(int id, String title) {
        super(id);
        this.title = title;
    }

    public Specialization(String title) {
        this.title = title;
    }

    public Specialization() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Specialization that = (Specialization) o;
        return Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
