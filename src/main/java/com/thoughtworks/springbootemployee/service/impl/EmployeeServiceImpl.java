package com.thoughtworks.springbootemployee.service.impl;

import com.thoughtworks.springbootemployee.dto.EmployeeRequestDto;
import com.thoughtworks.springbootemployee.dto.mapper.EmployeeRequestDtoMapper;
import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.exception.CompanyNotFoundException;
import com.thoughtworks.springbootemployee.exception.EmployeeNotFoundException;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final CompanyRepository companyRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               CompanyRepository companyRepository) {
        this.employeeRepository = employeeRepository;
        this.companyRepository = companyRepository;
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
    public void addEmployee(EmployeeRequestDto employeeRequestDto) throws CompanyNotFoundException {
        Integer companyId = employeeRequestDto.getCompanyId();
        Company company = companyRepository.findById(companyId).orElseThrow(CompanyNotFoundException::new);
        Employee employee = EmployeeRequestDtoMapper.toEntity(employeeRequestDto);
        employee.setCompany(company);
        employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Integer id) throws EmployeeNotFoundException {
        try {
            employeeRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EmployeeNotFoundException();
        }
    }

    @Override
    public void modifyEmployee(EmployeeRequestDto employeeRequestDto) throws CompanyNotFoundException, EmployeeNotFoundException {
        if (Objects.isNull(employeeRequestDto.getId())) {
            throw new EmployeeNotFoundException();
        }
        Optional<Employee> employee = employeeRepository.findById(employeeRequestDto.getId());
        if (employee.isPresent()) {
            addEmployee(employeeRequestDto);
        } else {
            throw new EmployeeNotFoundException();
        }
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
