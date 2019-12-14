package by.training.finalproject.model.entity;

public enum MasterStatus {
    WORKING("working"), FIRED("fired");
    private String statusValue;
    private MasterStatus(String statusValue) {
        this.statusValue = statusValue;
    }
    public String getStatusValue() {
        return statusValue;
    }
}
