package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.exception.CompanyIdNotFoundException;
import com.thoughtworks.springbootemployee.exception.CompanyNotFoundException;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public List<Company> getAllCompanies() {
        return companyService.getAllCompany();
    }

    @GetMapping("/{id}")
    public Company getCompanyById(@PathVariable Integer id) throws CompanyNotFoundException {
        return companyService.getCompanyById(id);
    }

    @PostMapping
    public void addCompany(Company company){
        companyService.addCompany(company);
    }

    @PutMapping
    public void modifyCompany(Company company) throws CompanyIdNotFoundException {
        companyService.modify(company);
    }

    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable Integer id){
        companyService.deleteCompany(id);
    }

    @GetMapping(params = {"page", "size"})
    public Page<Company> getPagingCompanies(@PageableDefault Pageable pageable) {
        return companyService.getPagingCompanies(pageable);
    }

    @GetMapping("/{id}/employees")
    public List<Employee> getEmployeesFromCompany(@PathVariable Integer id) throws CompanyNotFoundException {
        return companyService.getEmployeesFromCompany(id);
    }

}
