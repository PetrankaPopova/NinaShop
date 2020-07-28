package diplomna.model.entity;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Entity
@Table(name = "users") public class User extends BaseEntity implements UserDetails{

    private String username;
    private String password;
    private String email;
    private String userAddress;
    private String userPhone;
    private Set<UserRole> authorities;
    private Bag bag;

    public User() {
    }



    @Override
    @Column(name = "username",nullable = false)
    @Length(min = 3, max = 20, message = "Username length must be between 3 and 20 characters (inclusive 3 and 20).")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    @Column(name = "password", nullable = false)
    @Length(min = 3, max = 20, message = "Password length must be between 3 and 20 characters (inclusive 3 and 20).")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "email",nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "userAddress", nullable = false)
    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    @Column(name = "userPhone",nullable = false)
    @Pattern(regexp = "^\\d{10}$")
    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    public Set<UserRole> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<UserRole> authorities) {
        this.authorities = authorities;
    }

    @OneToOne(cascade = {CascadeType.ALL})
    public Bag getBag() {
        return bag;
    }

    public void setBag(Bag bag) {
        this.bag = bag;
    }


    @Override
    @Transient
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @Transient
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @Transient
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @Transient
    public boolean isEnabled() {
        return true;
    }


}
