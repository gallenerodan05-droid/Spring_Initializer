package com.template.Spring_Initializer.Model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

public class Teacher {


    @Id 
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long Id;
    
    private String firstname;
    private String lastname;

    @OneToMany(mappedBy = "teacher")
    private List<Grade> grades;

    public Teacher() {
    }
    public Teacher(String firstname, String lastname) {
        this.lastname = lastname;
        this.firstname = firstname;
    }

    public Long getId() {
        return Id;
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
