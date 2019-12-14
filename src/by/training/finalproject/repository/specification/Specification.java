package by.training.finalproject.repository.specification;

/**
 * The specification represents the rules of business logic
 * in the form of a {@code Boolean} predicate
 * that takes a domain entity as input.
 * Used to find entity that satisfy the concrete {@code Specification}.
 * @param <T> {@code Class} of a domain entity
 *
 *  @author  Angelina Shabaltas
 *  @version 1.0
 *  @since   2019-05-12
 */

public interface Specification<T> {
    /**
     * Define whether a domain entity satisfy this {@code Specification} or not.
     * @param bean a domain entity
     * @return {@code true} if the entity satisfy this specification,
     *          otherwise {@code false}
     */
    boolean match(T bean);

    String toSql();
}
