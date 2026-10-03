package com.template.Spring_Initializer.Repository;

import com.template.Spring_Initializer.Model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepo extends JpaRepository <Grade,Long> {
}
