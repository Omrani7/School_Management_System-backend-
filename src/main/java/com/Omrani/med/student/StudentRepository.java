package com.Omrani.med.student;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findAllByfirstnameContaining(String name);
    Optional<Student> findByUserName(String userName);
}
