package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.dto.CompanyRequestDto;
import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.exception.CompanyNameExistedException;
import com.thoughtworks.springbootemployee.exception.CompanyNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CompanyService {

    List<Company> getAllCompany();

    Company getCompanyById(Integer id) throws CompanyNotFoundException;

    void addCompany(CompanyRequestDto companyRequestDto) throws CompanyNameExistedException;

    void modify(CompanyRequestDto companyRequestDto) throws CompanyNotFoundException, CompanyNameExistedException;

    void deleteCompany(Integer id);

    Page<Company> getPagingCompanies(Pageable pageable);

    List<Employee> getEmployeesFromCompany(Integer id) throws CompanyNotFoundException;
}

