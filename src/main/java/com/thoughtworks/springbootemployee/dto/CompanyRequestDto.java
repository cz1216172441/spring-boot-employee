package com.thoughtworks.springbootemployee.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CompanyRequestDto {

    @NotNull
    private Integer companyId;

    @NotBlank
    @Size(min = 1, max = 32)
    private String name;

    public CompanyRequestDto() {
    }

    public CompanyRequestDto(Integer companyId, String name) {
        this.companyId = companyId;
        this.name = name;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
