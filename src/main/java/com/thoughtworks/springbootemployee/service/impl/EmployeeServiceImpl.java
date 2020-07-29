package com.thoughtworks.springbootemployee.service.impl;

import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.exception.EmployeeIdNotFoundException;
import com.thoughtworks.springbootemployee.exception.EmployeeNotFoundException;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Integer id) throws EmployeeNotFoundException {
        return employeeRepository.findById(id).orElseThrow(EmployeeNotFoundException::new);
    }

    @Override
    public void addEmployee(Employee employee) {

        employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Integer id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public void modifyEmployee(Employee employee) throws EmployeeIdNotFoundException {
        if (Objects.isNull(employee.getId())) {
            throw new EmployeeIdNotFoundException();
        }
        employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getEmployeeByGender(String gender) {
        return employeeRepository.findByGender(gender);
    }

    @Override
    public Page<Employee> getPagingEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }
}
