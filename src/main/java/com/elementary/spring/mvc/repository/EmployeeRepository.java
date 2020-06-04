package com.elementary.spring.mvc.repository;

import com.elementary.spring.mvc.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
