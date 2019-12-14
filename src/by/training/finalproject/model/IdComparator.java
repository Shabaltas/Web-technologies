package by.training.finalproject.model;

import java.util.Comparator;

/**
 * Utility class that is used to compare {@code Entity} instances
 * by their identifiers.
 *
 *  @author  Angelina Shabaltas
 *  @version 1.0
 *  @since   2019-05-12
 */
public final class IdComparator<T extends Entity> implements Comparator<T> {
    /**
     * Private constructor to create the only {@code IdComparator}.
     */
    private IdComparator() { }
    /**
     * The only instance of {@code IdComparator} that can be created.
     */
    private static IdComparator instance;
    /**
     * Static method to take the only {@code IdComparator}.
     * @return the only instance of {@code IdComparator}
     */
    public static IdComparator getInstance() {
        if (instance == null) {
            instance = new IdComparator();
        }
        return instance;
    }
    /**
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.
     * Uses {@code Integer.compare(int x, int y)} to compare
     * two {@code Entity} instances by their identifiers.
     *
     * @param  o1 the first {@code Entity} to compare
     * @param  o2 the second {@code Entity} to compare
     * @return the value {@code 0} if {@code o1.id == o2.id};
     *         a value less than {@code 0} if {@code o1.id < o2.id};
     *         a value greater than {@code 0} if {@code o1.id > o2.id}
     */
    @Override
    public int compare(final T o1, final T o2) {
        return Integer.compare(o1.getId(), o2.getId());
    }
}
