package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.exception.CompanyIdNotFoundException;
import com.thoughtworks.springbootemployee.exception.CompanyNotFoundException;

import java.util.List;

public interface CompanyService {

    List<Company> getAllCompany();

    Company getCompanyById(Integer id) throws CompanyNotFoundException;

    void addCompany(Company company);

    void modify(Company company) throws CompanyIdNotFoundException;
}
