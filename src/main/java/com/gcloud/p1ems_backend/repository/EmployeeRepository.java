package com.gcloud.p1ems_backend.repository;

import com.gcloud.p1ems_backend.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
