package com.loginsignup.models;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.NaturalId;
@Entity
@Table(name = "roles")
public class Roles {
    // property of Roles class Id, RoleType
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 70)
    private RoleType name;
    // Default Constractor
    public Roles() {
    }
    public Roles(RoleType name) {
        this.name = name;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public RoleType getName() {
        return name;
    }
    public void setName(RoleType name) {
        this.name = name;
    }
}