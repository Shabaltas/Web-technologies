package by.training.finalproject.model.specialization;

import java.util.Comparator;

public class TitleComparator implements Comparator<Specialization> {
    /**
     * Private constructor to create the only {@code TitleComparator}.
     */
    private TitleComparator() { }
    /**
     * The only instance of {@code TitleComparator} that can be created.
     */
    private static TitleComparator instance;
    /**
     * Static method to take the only {@code TitleComparator}.
     * @return the only instance of {@code TitleComparator}
     */
    public static TitleComparator getInstance() {
        if (instance == null) {
            instance = new TitleComparator();
        }
        return instance;
    }
    /**
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.
     * Uses {@code String.compareTo(int x, int y)} to compare
     * two {@code Specializations} instances by their titles alphabetically.
     *
     * @param  o1 the first {@code Specialization} to compare
     * @param  o2 the second {@code Specialization} to compare
     * @return the value {@code 0} if {@code o1.title == o2.title},
     *         a value less than {@code 0} if {@code o1.title < o2.title},
     *         a value greater than {@code 0} if {@code o1.title > co2.title}
     */
    @Override
    public int compare(Specialization o1, Specialization o2) {
        return o1.getTitle().compareToIgnoreCase(o2.getTitle());
    }
}
