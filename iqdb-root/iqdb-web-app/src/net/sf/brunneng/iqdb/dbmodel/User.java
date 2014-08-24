/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sf.brunneng.iqdb.dbmodel;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author goodg_000
 */
@Entity
@Table(name = "SITE_USER")
@AttributeOverrides({
    @AttributeOverride(name = "id", column = @Column(name = "U_ID"))
})
public class User extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "U_NAME")
    private String name;

    @Column(name = "U_EMAIL")
    private String email;

    @Column(name = "U_PASSWORD")
    private String password;

    @Column(name = "U_ROLE")
    private String role;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
