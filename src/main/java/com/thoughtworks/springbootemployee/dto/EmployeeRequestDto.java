package com.thoughtworks.springbootemployee.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EmployeeRequestDto {

    private Integer id;

    @NotBlank
    @Size(min = 1, max = 32)
    private String name;

    @NotNull
    @Min(value = 1)
    private Integer age;

    private String gender;

    @NotNull
    private Integer companyId;

    public EmployeeRequestDto() {
    }

    public EmployeeRequestDto(Integer id, String name, Integer age, String gender, Integer companyId) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.companyId = companyId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

}
