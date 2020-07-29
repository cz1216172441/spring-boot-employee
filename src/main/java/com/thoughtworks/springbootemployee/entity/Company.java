package com.thoughtworks.springbootemployee.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer companyId;

    private String name;

    @OneToMany(mappedBy = "company")
    private List<Employee> employees;

    public Company() {
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public String getName() {
        return name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

}