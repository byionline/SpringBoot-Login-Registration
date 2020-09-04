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
import com.loginsignup.util.CustomTimeStamp;
import org.hibernate.annotations.NaturalId;
@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }),
        @UniqueConstraint(columnNames = { "emailId" }) })
public class Usermodel extends CustomTimeStamp {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
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
    @Size(max = 70)
    @NaturalId
    @Email
    private String emailId;
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
    public String getEmailId() {
        return emailId;
    }
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Usermodel(String name, String username, String emailId, String password) {
        this.name = name;
        this.username = username;
        this.emailId = emailId;
        this.password = password;
    }
    public Usermodel() {
    }
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Roles> role = new HashSet<>();
    public Set<Roles> getRole() {
        return role;
    }
    public void setRole(Set<Roles> role) {
        this.role = role;
    }
}