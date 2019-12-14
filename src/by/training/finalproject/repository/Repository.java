package by.training.finalproject.repository;

import by.training.finalproject.model.Entity;
import by.training.finalproject.repository.specification.Specification;

import java.util.Comparator;
import java.util.List;

/**
 * Provides an abstract storage engine for entity collections.
 * @param <T> {@code Class} of entity to store.
 *
 *  @author  Angelina Shabaltas
 *  @version 1.0
 *  @since   2019-05-12
 */
public interface Repository<T extends Entity> {
    /**
     * Finds the objects in {@code Repository}
     * that satisfy the search specification.
     * @param spec specification that define search options.
     * @return the {@code List} of satisfying objects.
     */
    List<T> find(Specification<T> spec);
    /**
     * Finds the objects in {@code Repository}
     * that satisfy the search specification.
     * @param spec specification that define search options.
     * @return the {@code List} of satisfying objects.
     */
    List<T> find(List<Specification<T>> spec);

    /**
     * Sorts the objects in {@code Repository}
     * according to a certain {@code Comparator}.
     * @param comparator defines the sorting options.
     */
    List<T> sort(Comparator<T> comparator);

    List<T> sort();
    /**
     * Add the object in {@code Repository}.
     * @param object object to add
     */
    void add(T object);

    /**
     * Remove the object from {@code Repository}.
     * @param object object to remove
     */
    void remove(T object);

    /**
     * Remove the objects in {@code Repository}
     * that satisfy the search specification.
     * @param spec specification that define search options
     */
    void remove(Specification<T> spec);
    /**
     * Remove the objects in {@code Repository}
     * that satisfy the search specification.
     * @param spec specification that define search options
     */
    void remove(List<Specification<T>> spec);

    /**
     * Update the object in {@code Repository}.
     * @param object object to update
     */
    void update(int index, T object);

    /**
     * Returns an object from {@code Repository} with that {@code index}.
     * @param index index to find the element
     * @return an object with that index
     */
    T take(int index);

    int getSize();
}
