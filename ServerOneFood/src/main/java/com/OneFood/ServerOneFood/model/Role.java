package com.OneFood.ServerOneFood.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idRole;
    @Column(nullable = false)
    private String roleName;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY) //
    private List<User> users = new ArrayList<>();

    public Role( String roleName, List<User> users) {

        this.roleName = roleName;
        this.users = users;
    }

    public void addUser(User user) {
        users.add(user);
        user.getRoles().add(this);
    }

    public void removeUser(User user) {
        users.remove(user);
        user.getRoles().remove(this);
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) { //
        this.users = users;
    }

    public Role() {
    }

    public Role(String roleName) {
        this.roleName = roleName;
    }

    public long getIdRole() {
        return idRole;
    }

    public void setIdRole(long idRole) {
        this.idRole = idRole;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
