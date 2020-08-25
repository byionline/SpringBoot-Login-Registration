package com.loginsignup.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "user", uniqueConstraints = { 
@UniqueConstraint(columnNames = { "username" }),
@UniqueConstraint(columnNames = { "email_id" }) 
})
public class Usermodel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 20)
    @NotBlank
    private String name;

    @Size(max = 20)
    @NotBlank
    private String username;

    @NotBlank
    @Size(max = 100)
    @NaturalId
    @Email
    private String email_id;

    @NotBlank
    @Size(max = 100)
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Usermodel(String name, String username, String email_id, String password) {
        this.name = name;
        this.username = username;
        this.email_id = email_id;
        this.password = password;
    }

    public Usermodel() {
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name="user_id"),
    inverseJoinColumns = @JoinColumn(name="roles_id"))
    private Set<Roles> role = new HashSet<>();

    public Set<Roles> getRole() {
        return role;
    }

    public void setRole(Set<Roles> role) {
        this.role = role;
    }

    

    



}