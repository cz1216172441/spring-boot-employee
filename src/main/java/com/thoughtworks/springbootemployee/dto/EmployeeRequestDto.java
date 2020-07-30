package com.thoughtworks.springbootemployee.dto;

import com.thoughtworks.springbootemployee.entity.Employee;

public class EmployeeRequestDto {

    private Integer id;

    private String name;

    private Integer age;

    private String gender;

    private Integer companyId;

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

    public Employee toEntity() {
        return new Employee(this.id, this.name, this.age, this.gender);
    }

    @Override
    public String toString() {
        return "EmployeeRequestDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", companyId=" + companyId +
                '}';
    }
}
