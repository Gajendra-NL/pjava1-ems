package com.gcloud.p1ems_backend.service.impl;

import com.gcloud.p1ems_backend.dto.EmployeeDto;
import com.gcloud.p1ems_backend.entity.Employee;
import com.gcloud.p1ems_backend.exception.ResourceNotFoundException;
import com.gcloud.p1ems_backend.mapper.EmployeeMapper;
import com.gcloud.p1ems_backend.repository.EmployeeRepository;
import com.gcloud.p1ems_backend.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee doesn not exist with the given id : " + employeeId));

        return  EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employeeList = employeeRepository.findAll();
        return employeeList.stream()
                .map((e) -> EmployeeMapper.mapToEmployeeDto(e))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployeeData) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("User doesn't exist")
        );

        employee.setFirstName(updatedEmployeeData.getFirstName());
        employee.setLastName(updatedEmployeeData.getLastName());
        employee.setEmail(updatedEmployeeData.getEmail());

        Employee updatedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(updatedEmployee);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
            .orElseThrow(() -> new ResourceNotFoundException("User doesn't exist")
        );

        employeeRepository.deleteById(employeeId);
    }
}
