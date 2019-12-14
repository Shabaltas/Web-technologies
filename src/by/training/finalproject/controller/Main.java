package by.training.finalproject.controller;

import by.training.finalproject.model.IdComparator;
import by.training.finalproject.model.specialization.Specialization;
import by.training.finalproject.model.specialization.TitleComparator;
import by.training.finalproject.repository.impl.specialization.ByTitleSpecification;
import by.training.finalproject.repository.specification.ByIdSpecification;
import by.training.finalproject.repository.specification.Specification;
import by.training.finalproject.service.Service;
import by.training.finalproject.service.SpecializationService;
import by.training.finalproject.utilities.JAXBSerializer;
import by.training.finalproject.repository.impl.specialization.SpecializationListRepository;
/*import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;*/

import java.util.ArrayList;
import java.util.List;

public class Main {
    //private static final Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        SpecializationListRepository repository = null;
        try {
            repository = new JAXBSerializer<SpecializationListRepository>()
                    .deserialize("withId.txt", SpecializationListRepository.class);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
        Service<Specialization> service = SpecializationService.getInstance(repository);
        service.getAll().forEach(System.out::println);
        System.out.println("SORTED");
        List<Specialization> list = service.sort(IdComparator.getInstance());
        list.forEach(System.out::println);
        list.add(new Specialization("random"));
        System.out.println("ADDED");
        list.forEach(System.out::println);
        service.add(new Specialization("WWWWW"));
        System.out.println("REP");
        service.sort(TitleComparator.getInstance().thenComparing(IdComparator.getInstance())).forEach(System.out::println);
        List<Specification<Specialization>> specifications = new ArrayList<>();
        specifications.add(new ByTitleSpecification("rrr"));
        specifications.add(new ByIdSpecification<>(4));
        System.out.println("DELETE 4 and rrr");
        service.remove(specifications);
        service.getAll().forEach(System.out::println);
        try {
            new JAXBSerializer<SpecializationListRepository>().serialize("withIdAfter.txt", repository);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
