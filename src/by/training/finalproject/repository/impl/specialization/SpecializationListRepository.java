package by.training.finalproject.repository.impl.specialization;

import by.training.finalproject.model.specialization.Specialization;
import by.training.finalproject.repository.ListRepository;
import by.training.finalproject.repository.specification.ByIdSpecification;
//import lombok.ToString;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Arrays;
import java.util.List;
//@ToString
@XmlRootElement(name="SpecializationsRepository")
public class SpecializationListRepository extends ListRepository<Specialization> {
    @Override
    public String toString() {
        return "SpecializationListRepository{" +
                "entitiesList=" + Arrays.toString(entitiesList.toArray()) +
                '}';
    }

    /**
     * The only instance of {@code SpecializationListRepository} that can be created.
     */
    private static SpecializationListRepository instance;
    /**
     * Private constructor to create the only {@code SpecializationListRepository}.
     */
    private SpecializationListRepository() { }
    /**
     * Static method to take the only {@code SpecializationListRepository}.
     * @return the only instance of {@code SpecializationListRepository}
     */
    public static SpecializationListRepository getInstance() {
        if (instance == null) {
            instance = new SpecializationListRepository();
        }
        return instance;
    }

    public void update(int index, Specialization specialization) {
        List<Specialization> specializations = find(new ByIdSpecification(index));
        if (specializations.isEmpty()) return;
        specializations.get(0).setTitle(specialization.getTitle());
    }
}
