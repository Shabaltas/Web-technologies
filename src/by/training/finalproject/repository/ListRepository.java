package by.training.finalproject.repository;

import by.training.finalproject.model.Entity;
import by.training.finalproject.model.specialization.Specialization;
import by.training.finalproject.repository.specification.ByIdSpecification;
import by.training.finalproject.repository.specification.Specification;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@XmlSeeAlso({Specialization.class})
public abstract class ListRepository<T extends Entity> implements Repository<T>{
    @XmlElement(name = "nextId")
    private int nextId = 1;
    @XmlAnyElement(lax=true)
    protected List<T> entitiesList = new ArrayList<>();
    @Override
    public List<T> find(Specification<T> spec) {
        List<T> result = new ArrayList<>();
        entitiesList.forEach(session -> {
            if (spec.match(session)) {
                result.add(session);
            }
        });
        return result;
    }

    @Override
    public List<T> find(List<Specification<T>> specs) {
        List<T> result = new ArrayList<>();
        boolean match;
        for (T session: entitiesList) {
            match = true;
            int i = 0;
            while (match && i < specs.size()) {
                match = specs.get(i).match(session);
            }
            if (match) result.add(session);
        }
        return result;
    }

    @Override
    public List<T> sort(Comparator<T> comparator) {
        entitiesList.sort(comparator);
        return new ArrayList<>(entitiesList);
    }

    @Override
    public List<T>  sort() {
        entitiesList.sort(null);
        return new ArrayList<>(entitiesList);
    }

    /**
     * Add the {@code T} in this {@code SpecListRepository}.
     * @param newT {@code T} to add
     */
    public void add(T newT) {
        if (newT.getId() == 0) {
            newT.setId(nextId++);
        }
        List<T> sameIdCar = find(new ByIdSpecification(newT.getId()));
        if (sameIdCar.isEmpty()) {
            entitiesList.add(newT);
        } else {
            entitiesList.remove(sameIdCar.get(0));
            entitiesList.add(newT);
        }
    }
    /**
     * Remove the {@code T} from this {@code SpecListRepository}.
     * @param T {@code T} to remove
     */
    public void remove(T T) {
        entitiesList.remove(T);
    }
    /**
     * Find the {@code T} objects in this {@code SpecListRepository}
     * that satisfy the search specification
     * and remove them.
     * @param spec specification that define search options
     */
    public void remove(Specification<T> spec) {
        int i = 0;
        while (i < entitiesList.size()) {
            if (spec.match(entitiesList.get(i))) {
                entitiesList.remove(i);
            } else {
                i++;
            }
        }
    }

    @Override
    public void remove(List<Specification<T>> specs) {
        int i = 0;
        boolean match;
        T session;
        while (i < entitiesList.size()) {
            match = true;
            int j = 0;
            session = entitiesList.get(i);
            while (match && j < specs.size()) {
                match = specs.get(j++).match(session);
            }
            if (match) {
                entitiesList.remove(i);
            }
            else i++;
        }
    }

    public T take(int index) {
        return entitiesList.get(index);
    }

    @Override
    public int getSize() {
        return entitiesList.size();
    }
}
