package by.training.finalproject.service;

import by.training.finalproject.model.IdComparator;
import by.training.finalproject.model.specialization.Specialization;
import by.training.finalproject.repository.Repository;
import by.training.finalproject.repository.specification.Specification;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SpecializationService extends Service<Specialization> {
    private static volatile SpecializationService instance;

    public static SpecializationService getInstance(Repository<Specialization> repository) {
        if (instance == null) {
            synchronized (SpecializationService.class) {
                if (instance == null) {
                    instance = new SpecializationService(repository);
                }
            }
        }
        return instance;
    }

    public static SpecializationService getInstance() {
        return instance;
    }

    private SpecializationService(Repository<Specialization> repository) {
        super(repository);
    }

    @Override
    public void add(Specialization entity) {
        repository.add(entity);
    }


    @Override
    public List<Specialization> sort() {
        return repository.sort(IdComparator.getInstance());
    }

    @Override
    public List<Specialization> sort(Comparator<Specialization> comparator) {
        return repository.sort(comparator);
    }

    @Override
    public Specialization get(int index) {
        return repository.take(index);
    }

    @Override
    public List<Specialization> find(Specification<Specialization> specification) {
        return repository.find(specification);
    }

    @Override
    public List<Specialization> find(List<Specification<Specialization>> specifications) {
        return repository.find(specifications);
    }

    @Override
    public void remove(Specification<Specialization> specification) {
        repository.remove(specification);
    }

    @Override
    public void remove(List<Specification<Specialization>> specifications) {
        repository.remove(specifications);
    }

    @Override
    public List<Specialization> getAll() {
        List<Specialization> all = new ArrayList<>();
        for (int i = 0; i < repository.getSize(); i++) {
            all.add(repository.take(i));
        }
        return all;
    }


}
