package diplomna.model.bindingmodel;

import javax.validation.constraints.Pattern;

public class SaveNewRolesBindingModel {

    private String userId;
    private String newRole;

    public SaveNewRolesBindingModel() {
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
