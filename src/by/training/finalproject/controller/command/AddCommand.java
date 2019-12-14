package by.training.finalproject.controller.command;

import by.training.finalproject.model.specialization.Specialization;
import by.training.finalproject.service.SpecializationService;

public class AddCommand implements Command {
    @Override
    public String execute(String[] args) {
        Specialization specialization = new Specialization(args[0]);
        SpecializationService service;
        if ((service = SpecializationService.getInstance()) != null ) {
            service.add(specialization);
            return "done";
        }
        return "no dao";
    }
}
