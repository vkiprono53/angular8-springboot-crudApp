package com.kiprono.employee.services;

import com.kiprono.employee.models.Employee;
import com.kiprono.employee.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;
@Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    public List<Employee> getAllEmployees(){
    return employeeRepository.findAll();
    }

    public Optional<Employee> findEmployeeById(Long id){
    return employeeRepository.findById(id);

    }
    public Employee createEmployee(Employee employee){
    return employeeRepository.save(employee);
    }

    public void deleteEmployees(Long id){
    employeeRepository.deleteById(id);
    }
}
