package by.training.finalproject.model.entity;

public enum Role {
    ADMIN("admin"), CLIENT("client"), MASTER("master");
    private String roleValue;
    private Role(String roleValue) {
        this.roleValue = roleValue;
    }
    public String getRoleValue() {
        return roleValue;
    }
}
