package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.dto.EmployeeRequestDto;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.exception.CompanyNotFoundException;
import com.thoughtworks.springbootemployee.exception.EmployeeNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployees();

    Employee getEmployeeById(Integer id) throws EmployeeNotFoundException;

    void addEmployee(EmployeeRequestDto employeeRequestDto) throws CompanyNotFoundException;

    void deleteEmployee(Integer id) throws EmployeeNotFoundException;

    void modifyEmployee(EmployeeRequestDto employeeRequestDto) throws CompanyNotFoundException, EmployeeNotFoundException;

    List<Employee> getEmployeeByGender(String gender);

    Page<Employee> getPagingEmployees(Pageable pageable);
}
