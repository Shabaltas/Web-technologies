package by.training.finalproject.repository.impl.session;

import by.training.finalproject.model.entity.Session;
import by.training.finalproject.repository.ListRepository;
import by.training.finalproject.repository.specification.ByIdSpecification;

import java.util.List;

public class SessionListRepository extends ListRepository<Session> {
    private int increment;
    /**
     * The only instance of {@code CarRepository} that can be created.
     */
    private static SessionListRepository instance;
    /**
     * Private constructor to create the only {@code CarListRepository}.
     */
    private SessionListRepository() { }
    /**
     * Static method to take the only {@code CarRepository}.
     * @return the only instance of {@code CarRepository}
     */
    public static SessionListRepository getInstance() {
        if (instance == null) {
            instance = new SessionListRepository();
        }
        return instance;
    }

    public void update(int index, Session newT) {
        List<Session> sessions = find(new ByIdSpecification(index));
        if (sessions.isEmpty()) return;
        Session oldT = sessions.get(0);
        oldT.setDate(newT.getDate());
        oldT.setIdClient(newT.getIdClient());
        oldT.setIdMaster(newT.getIdMaster());
        oldT.setIdService(newT.getIdService());
        oldT.setTime(newT.getTime());
    }
}
