package com.template.Spring_Initializer.Model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


@Entity 
public class Student {



    
    @Id 
    @GeneratedValue(strategy= GenerationType.IDENTITY )
    private Long id;


    private String firstname;
    private String lastname;

    @OneToMany(mappedBy = "student")
    private List<Grade> grades;

    
    // TODO  empty constructor for hibernate
    public Student(){
    }
    // TODO id is automatically generated that is why it is unnecessary to initialize it.
    public Student(String firstname,String lastname ){
        this.firstname = firstname;
        this.lastname = lastname;
    }

//    TODO an Id is not supposed to have a setter because id should be immutable values.
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
