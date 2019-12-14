package by.training.finalproject.service;

import by.training.finalproject.model.Entity;
import by.training.finalproject.repository.Repository;
import by.training.finalproject.repository.specification.Specification;

import java.util.Comparator;
import java.util.List;

//TODO make singleton
public abstract class Service<T extends Entity> {
    protected final Repository<T> repository;

    protected Service(Repository<T> repository) {
        this.repository = repository;
    }

    public abstract void add(T entity);
    public abstract List<T> sort();
    public abstract  List<T> sort(Comparator<T> comparator);
    public abstract T get(int index);
    public abstract List<T> find(Specification<T> specification);
    public abstract List<T> find(List<Specification<T>> specification);
    public abstract void remove(Specification<T> specification);
    public abstract void remove(List<Specification<T>> specification);
    public abstract List<T> getAll();

/*    //service does the check
    public List<T> find(Spec) {
        List<T> list = new ArrayList<>();

        return list;
    }
    List<T> sort();
    //true if deleted successful
    boolean remove();
    //true if updated successful
    boolean update();
    T take();*/
}
