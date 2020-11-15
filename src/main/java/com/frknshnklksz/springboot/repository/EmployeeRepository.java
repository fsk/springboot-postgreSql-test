package com.frknshnklksz.springboot.repository;

import com.frknshnklksz.springboot.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findEmployeeByEmail(String email);
    Employee findEmployeeByUserName(String userName);

}
