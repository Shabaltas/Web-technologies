package by.training.finalproject.repository.specification;

import by.training.finalproject.model.Entity;

public class ByIdSpecification<T extends Entity> implements Specification<T> {
    /**
     * Desired identifier.
     */
    private long id;
    /**
     * Constructs new {@code ByIDSpecification}
     * and initialize desired identifier {@code id}.
     *
     * @param identifier desired identifier
     */
    public ByIdSpecification(long identifier) {
        this.id = identifier;
    }

    public void setId(long id) {
        this.id = id;
    }

    /**
     * Define whether a domain entity satisfy
     * this {@code ByIDSpecification} or not.
     * @param entity a domain entity
     * @return {@code true} if the {@code Entity.id} equal to desired identifier,
     *          otherwise {@code false}
     */

    @Override
    public boolean match(Entity entity) {
        return id == entity.getId();
    }

    @Override
    public String toSql() {
        return "id=" + id;
    }
}
