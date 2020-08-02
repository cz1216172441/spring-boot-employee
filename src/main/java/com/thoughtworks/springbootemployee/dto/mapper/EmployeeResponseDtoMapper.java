package com.thoughtworks.springbootemployee.dto.mapper;

import com.thoughtworks.springbootemployee.dto.EmployeeResponseDto;
import com.thoughtworks.springbootemployee.entity.Employee;

import java.util.Objects;

public class EmployeeResponseDtoMapper {

    public static EmployeeResponseDto toEmployeeResponseDto(Employee employee) {
        EmployeeResponseDto dto = new EmployeeResponseDto(employee.getId(), employee.getName(), employee.getAge(), employee.getGender());
        if (Objects.nonNull(employee.getCompany())) {
            dto.setCompanyName(employee.getCompany().getName());
        }
        return dto;
    }
}
