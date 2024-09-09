package project_fullstack.example.ems_backends.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import project_fullstack.example.ems_backends.dto.EmployeeDto;
import project_fullstack.example.ems_backends.entity.Employee;
import project_fullstack.example.ems_backends.exception.ResourceNotFoundException;
import project_fullstack.example.ems_backends.mapper.EmployeeMapper;
import project_fullstack.example.ems_backends.repository.EmployeeRepository;
import project_fullstack.example.ems_backends.service.EmployeeService;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployeeDto(EmployeeDto employeeDto) {
        Employee employee =  EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
       Employee employee =  employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee is not exists with the given id " + id));
                return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(employee -> EmployeeMapper.mapToEmployeeDto(employee))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployeeDto(Long employeeId, EmployeeDto updatedEmployees) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Employee is not exists with the given id " + employeeId));

        employee.setFirstName(updatedEmployees.getFirstName());
        employee.setLastName(updatedEmployees.getLastName());
        employee.setEmail(updatedEmployees.getEmail());

        Employee savedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }


    @Override
    public void deleteEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee is not exists with the given id " + id));
        employeeRepository.delete(employee);

    }
}
