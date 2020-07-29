package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.exception.EmployeeNotFoundException;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployees();

    Employee getEmployeeById(Integer id) throws EmployeeNotFoundException;
}