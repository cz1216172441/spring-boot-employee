package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.dto.EmployeeRequestDto;
import com.thoughtworks.springbootemployee.dto.EmployeeResponseDto;
import com.thoughtworks.springbootemployee.exception.ArgumentNotValidException;
import com.thoughtworks.springbootemployee.exception.CompanyNotFoundException;
import com.thoughtworks.springbootemployee.exception.EmployeeNotFoundException;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<EmployeeResponseDto> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public EmployeeResponseDto getEmployeeById(@PathVariable Integer id) throws EmployeeNotFoundException {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping(params = "gender")
    public List<EmployeeResponseDto> getEmployeeByGender(String gender) {
        return employeeService.getEmployeeByGender(gender);
    }

    @GetMapping(params = {"page", "size"})
    public Page<EmployeeResponseDto> getPagingEmployees(@PageableDefault Pageable pageable) {
        return employeeService.getPagingEmployees(pageable);
    }

    @PostMapping
    public void addEmployee(@RequestBody @Validated EmployeeRequestDto employeeRequestDto)
            throws CompanyNotFoundException, ArgumentNotValidException {
        if (Objects.nonNull(employeeRequestDto.getId())) {
            throw new ArgumentNotValidException("id: must be null");
        }
        employeeService.addEmployee(employeeRequestDto);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Integer id) throws EmployeeNotFoundException {
        employeeService.deleteEmployee(id);
    }

    @PutMapping
    public void modifyEmployee(@RequestBody @Validated EmployeeRequestDto employeeRequestDto)
            throws CompanyNotFoundException, EmployeeNotFoundException, ArgumentNotValidException {
        if (Objects.isNull(employeeRequestDto.getId())) {
            throw new ArgumentNotValidException("id: must not be null");
        }
        employeeService.modifyEmployee(employeeRequestDto);
    }

}
