package com.codegym.model;

import javax.persistence.*;

@Entity
@Table(name = "App_User", uniqueConstraints = {@UniqueConstraint(name = "APP_USER_UK", columnNames = "User_Name")})
public class AppUser {


    @Id
    @GeneratedValue
    @Column(name = "User_id", nullable = false)
    private Long id;

    @Column(name = "User_Name", length = 36, nullable = false)
    private String userName;

    @Column(name = "Password_Is_Encrypted", length = 128, nullable = false)
    private String passwordIsEncrypted;

    @Column(name = "Enabled", length = 1, nullable = false)
    private boolean enabled;

    public AppUser() {

    }

    public AppUser(Long id, String userName, String passwordIsEncrypted, boolean enabled) {
        this.id = id;
        this.userName = userName;
        this.passwordIsEncrypted = passwordIsEncrypted;
        this.enabled = enabled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasswordIsEncrypted() {
        return passwordIsEncrypted;
    }

    public void setPasswordIsEncrypted(String passwordIsEncrypted) {
        this.passwordIsEncrypted = passwordIsEncrypted;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

}
