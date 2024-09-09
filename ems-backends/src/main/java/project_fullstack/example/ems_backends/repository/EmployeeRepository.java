package project_fullstack.example.ems_backends.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import project_fullstack.example.ems_backends.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
