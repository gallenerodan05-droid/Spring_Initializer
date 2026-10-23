package com.template.Spring_Initializer.Model;

import java.util.List;

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


// Why won't this run in intellij?    "Java file is located outside of the module source root, so it won't be compiled " It has this and  /home/dalter124/.jdks/openjdk-26.0.2.1/bin/java -javaagent:/usr/share/idea/lib/idea_rt.jar=39797 -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -classpath /home/dalter124/Desktop/Spring Initalizer/Template/Spring_Initializer/target/test-classes:/home/dalter124/Desktop/Spring Initalizer/Template/Spring_Initializer/target/classes com.template.Spring_Initializer.SpringInitializerApplication
// Exception in thread "main" java.lang.NoClassDefFoundError: org/springframework/boot/SpringApplication
// 	at com.template.Spring_Initializer.SpringInitializerApplication.main(SpringInitializerApplication.java:10)
// Caused by: java.lang.ClassNotFoundException: org.springframework.boot.SpringApplication
// 	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(BuiltinClassLoader.java:580)
// 	at java.base/java.lang.ClassLoader.loadClass(ClassLoader.java:502)
// 	... 1 more

// Process finished with exit code 1