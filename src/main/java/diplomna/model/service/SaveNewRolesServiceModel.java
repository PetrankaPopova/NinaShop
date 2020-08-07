package diplomna.model.service;

import javax.validation.constraints.Pattern;

public class SaveNewRolesServiceModel extends BaseEntityService{

    private String userId;
    private String newRole;

    public SaveNewRolesServiceModel() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNewRole() {
        return newRole;
    }

    public void setNewRole(String newRole) {
        this.newRole = newRole;
    }
}
