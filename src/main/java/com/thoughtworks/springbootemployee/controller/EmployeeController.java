package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.dto.EmployeeRequestDto;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.exception.CompanyNotFoundException;
import com.thoughtworks.springbootemployee.exception.EmployeeIdNotFoundException;
import com.thoughtworks.springbootemployee.exception.EmployeeNotFoundException;
import com.thoughtworks.springbootemployee.log.WebApiLog;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @WebApiLog(name = "getAllEmployees")
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @WebApiLog(name = "getEmployeeById")
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Integer id) throws EmployeeNotFoundException {
        return employeeService.getEmployeeById(id);
    }

    @WebApiLog(name = "getEmployeeByGender")
    @GetMapping(params = "gender")
    public List<Employee> getEmployeeByGender(String gender) {
        return employeeService.getEmployeeByGender(gender);
    }

    @WebApiLog(name = "getPagingEmployees")
    @GetMapping(params = {"page", "size"})
    public Page<Employee> getPagingEmployees(@PageableDefault Pageable pageable) {
        return employeeService.getPagingEmployees(pageable);
    }

    @WebApiLog(name = "addEmployee")
    @PostMapping
    public void addEmployee(@RequestBody EmployeeRequestDto employeeRequestDto) throws CompanyNotFoundException {
        employeeService.addEmployee(employeeRequestDto);
    }

    @WebApiLog(name = "deleteEmployee")
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
    }

    @WebApiLog(name = "modifyEmployee")
    @PutMapping
    public void modifyEmployee(@RequestBody EmployeeRequestDto employeeRequestDto) throws EmployeeIdNotFoundException, CompanyNotFoundException, EmployeeNotFoundException {
        employeeService.modifyEmployee(employeeRequestDto);
    }

}
