package diplomna.model.service;

public class UserRoleServiceModel extends BaseEntityService{

    private String authority;

    public UserRoleServiceModel() {
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
