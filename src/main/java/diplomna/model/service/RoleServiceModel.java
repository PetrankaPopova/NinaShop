package diplomna.model.service;

public class RoleServiceModel extends BaseEntityService{

    private String authority;

    public RoleServiceModel() {
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
