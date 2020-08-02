package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.exception.CompanyNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CompanyService {

    List<Company> getAllCompany();

    Company getCompanyById(Integer id) throws CompanyNotFoundException;

    void addCompany(Company company);

    void modify(Company company) throws CompanyNotFoundException;

    void deleteCompany(Integer id);

    Page<Company> getPagingCompanies(Pageable pageable);

    List<Employee> getEmployeesFromCompany(Integer id) throws CompanyNotFoundException;
}

