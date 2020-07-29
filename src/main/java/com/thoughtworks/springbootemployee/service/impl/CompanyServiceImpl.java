package com.thoughtworks.springbootemployee.service.impl;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.exception.CompanyIdNotFoundException;
import com.thoughtworks.springbootemployee.exception.CompanyNotFoundException;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompany() {
        return companyRepository.findAll();
    }

    @Override
    public Company getCompanyById(Integer id) throws CompanyNotFoundException {
        return companyRepository.findById(id).orElseThrow(CompanyNotFoundException::new);
    }

    @Override
    public void addCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public void modify(Company company) throws CompanyIdNotFoundException {
        if (company.getCompanyId() == null) {
            throw new CompanyIdNotFoundException();
        }
        companyRepository.save(company);
    }

    @Override
    public void deleteCompany(Integer id) {
        companyRepository.deleteById(id);
    }
}
