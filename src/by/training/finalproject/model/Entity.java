package by.training.finalproject.model;

//import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/*@Getter
@AllArgsConstructor
@NoArgsConstructor*/
@XmlAccessorType(XmlAccessType.FIELD)
public class Entity implements Comparable<Entity> {
    @XmlAttribute
    private int id;
    public void setId(int i) {
        this.id = i;
    }

    @Override
    public String toString() {
        return "id=" + id;
    }

    @Override
    public int compareTo(Entity o) {
        return id - o.id;
    }

    public Entity(int id) {
        this.id = id;
    }

    public Entity(){}

    public int getId() {
        return id;
    }
}
