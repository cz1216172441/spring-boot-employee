package com.thoughtworks.springbootemployee.service.impl;

import com.thoughtworks.springbootemployee.dto.CompanyRequestDto;
import com.thoughtworks.springbootemployee.dto.mapper.CompanyRequestDtoMapper;
import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.exception.CompanyNameExistedException;
import com.thoughtworks.springbootemployee.exception.CompanyNotFoundException;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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
    public void addCompany(CompanyRequestDto companyRequestDto) throws CompanyNameExistedException {
        Company company = CompanyRequestDtoMapper.toEntity(companyRequestDto);
        company.setCompanyId(null);
        if (companyRepository.findByName(company.getName()).isPresent()) {
            throw new CompanyNameExistedException();
        }
        companyRepository.save(company);
    }

    @Override
    public void modify(CompanyRequestDto companyRequestDto) throws CompanyNotFoundException, CompanyNameExistedException {
        Company company = CompanyRequestDtoMapper.toEntity(companyRequestDto);
        if (!companyRepository.findById(company.getCompanyId()).isPresent()) {
            throw new CompanyNotFoundException();
        }
        Company existedCompany = companyRepository.findByName(company.getName()).orElse(null);
        if (Objects.nonNull(existedCompany) && !existedCompany.getCompanyId().equals(company.getCompanyId())) {
            throw new CompanyNameExistedException();
        }
        companyRepository.save(company);
    }

    @Override
    public void deleteCompany(Integer id) {
        companyRepository.deleteById(id);
    }

    @Override
    public Page<Company> getPagingCompanies(Pageable pageable) {
        return companyRepository.findAll(pageable);
    }

    @Override
    public List<Employee> getEmployeesFromCompany(Integer id) throws CompanyNotFoundException {
        return this.getCompanyById(id).getEmployees();
    }
}
