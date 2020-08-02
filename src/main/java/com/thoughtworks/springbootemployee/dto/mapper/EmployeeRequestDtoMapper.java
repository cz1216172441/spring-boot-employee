package com.thoughtworks.springbootemployee.dto.mapper;

import com.thoughtworks.springbootemployee.dto.EmployeeRequestDto;
import com.thoughtworks.springbootemployee.entity.Employee;

public class EmployeeRequestDtoMapper {
    public static Employee toEntity(EmployeeRequestDto employeeRequestDto) {
        return new Employee();
    }
}
