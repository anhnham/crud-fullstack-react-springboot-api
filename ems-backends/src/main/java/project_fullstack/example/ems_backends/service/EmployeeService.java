package project_fullstack.example.ems_backends.service;

import project_fullstack.example.ems_backends.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto createEmployeeDto(EmployeeDto employeeDto);

    EmployeeDto getEmployeeById(Long id);

    List<EmployeeDto> getAllEmployees();

    EmployeeDto updateEmployeeDto(Long employeeId, EmployeeDto updatedEmployees);

    void deleteEmployeeById(Long id);
}
