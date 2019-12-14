package by.training.finalproject.repository.impl.specialization;

import by.training.finalproject.model.specialization.Specialization;
import by.training.finalproject.repository.specification.Specification;

public class ByTitleSpecification implements Specification<Specialization> {
    /**
     * Desired title.
     */
    private String title;
    /**
     * Constructs new {@code ByIDSpecification}
     * and initialize desired identifier {@code id}.
     *
     * @param title desired title
     */
    public ByTitleSpecification(String title) {
        this.title = title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Define whether a domain entity satisfy
     * this {@code ByIDSpecification} or not.
     * @param bean a domain entity
     * @return {@code true} if the {@code Specialization.title} equal to desired identifier,
     *          otherwise {@code false}
     */
    @Override
    public boolean match(Specialization bean) {
        return bean.getTitle().equals(title);
    }

    @Override
    public String toSql() {
        return "title='" + title + "'";
    }
}
