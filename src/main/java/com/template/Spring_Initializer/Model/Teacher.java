package com.template.Spring_Initializer.Model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity 
public class Teacher {


    @Id 
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable= false)
    private String firstname;

    @Column(nullable= false)
    private String lastname;

    @JsonIgnore
    @OneToMany(mappedBy = "teacher")
    private List<Grade> grades;

    public Teacher() {
    }
    public Teacher(String firstname, String lastname) {
        this.lastname = lastname;
        this.firstname = firstname;
    }

    public Long getId() {
        return id;
    }


    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }





    
    
    
}
