package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.dto.CompanyRequestDto;
import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.exception.ArgumentNotValidException;
import com.thoughtworks.springbootemployee.exception.CompanyNameExistedException;
import com.thoughtworks.springbootemployee.exception.CompanyNotFoundException;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

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
    public void addCompany(@RequestBody CompanyRequestDto companyRequestDto)
            throws ArgumentNotValidException, CompanyNameExistedException {
        if (Objects.nonNull(companyRequestDto.getCompanyId())) {
            throw new ArgumentNotValidException("companyId: must be null");
        }
        companyService.addCompany(companyRequestDto);
    }

    @PutMapping
    public void modifyCompany(@RequestBody @Validated CompanyRequestDto companyRequestDto)
            throws CompanyNotFoundException, ArgumentNotValidException, CompanyNameExistedException {
        if (Objects.isNull(companyRequestDto.getCompanyId())) {
            throw new ArgumentNotValidException("companyId: must not be null");
        }
        companyService.modify(companyRequestDto);
    }

    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable Integer id) {
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
