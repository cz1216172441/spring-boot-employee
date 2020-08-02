package com.thoughtworks.springbootemployee.dto;

import com.thoughtworks.springbootemployee.dto.mapper.CompanyRequestDtoMapper;
import com.thoughtworks.springbootemployee.entity.Company;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompanyRequestDtoMapperTest {
    @Test
    void should_return_a_company_when_to_entity_given_a_company_request_dto() {
        //given
        CompanyRequestDto companyRequestDto = new CompanyRequestDto(2,"oocl");
        //when
        Company company = CompanyRequestDtoMapper.toEntity(companyRequestDto);
        //then
        assertEquals(2, company.getCompanyId());
        assertEquals("oocl", company.getName());
    }
}
