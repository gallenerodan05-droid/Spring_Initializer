package com.template.Spring_Initializer.Repository;

import com.template.Spring_Initializer.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository <Student,Long> {
}
