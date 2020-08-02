package com.thoughtworks.springbootemployee.dto.mapper;

import com.thoughtworks.springbootemployee.dto.CompanyRequestDto;
import com.thoughtworks.springbootemployee.entity.Company;

public class CompanyRequestDtoMapper {
    public static Company toEntity(CompanyRequestDto companyRequestDto) {
        return new Company(companyRequestDto.getCompanyId(), companyRequestDto.getName());
    }
}
