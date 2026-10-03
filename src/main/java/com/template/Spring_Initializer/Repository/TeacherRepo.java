package com.template.Spring_Initializer.Repository;

import com.template.Spring_Initializer.Model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepo extends JpaRepository<Teacher,Long> {
}
