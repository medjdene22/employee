package com.imad.employee.Service;

import com.imad.employee.entity.Employee;
import com.imad.employee.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public Employee postEmp(Employee employee){
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployee(){
        return employeeRepository.findAll();
    }

    public void deleteEmployee(Long id){
        if(!employeeRepository.existsById(id)){
            throw new EntityNotFoundException("Employee with id : " + id + " not found");
        }
        employeeRepository.deleteById(id);
    }

    public Employee getById(Long id){
        return employeeRepository.findById(id).orElse(null);
    }

    public Employee updateEmp(Long id,Employee employee){
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);

        if (optionalEmployee.isPresent()){
            Employee existEmployee = optionalEmployee.get();
            existEmployee.setEmail(employee.getEmail());
            existEmployee.setName(employee.getName());
            existEmployee.setPhoneNumber(employee.getPhoneNumber());
            existEmployee.setPosition(employee.getPosition());

            return employeeRepository.save(existEmployee);
        }
        return null;
    }
}
