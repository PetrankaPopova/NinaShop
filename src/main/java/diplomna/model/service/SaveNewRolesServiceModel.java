package diplomna.model.service;

public class SaveNewRolesServiceModel {

    private String roleAdmin;
    private String userId;

    public SaveNewRolesServiceModel() {
    }

    public String getRoleAdmin() {
        return roleAdmin;
    }

    public void setRoleAdmin(String roleAdmin) {
        this.roleAdmin = roleAdmin;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
