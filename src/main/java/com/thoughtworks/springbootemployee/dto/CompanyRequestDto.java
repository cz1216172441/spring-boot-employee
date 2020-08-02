package com.thoughtworks.springbootemployee.dto;

public class CompanyRequestDto {
    private Integer companyId;

    private String name;

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
