package com.template.Spring_Initializer.Repository;

import com.template.Spring_Initializer.Model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepo extends JpaRepository<Subject,Long> {
}
