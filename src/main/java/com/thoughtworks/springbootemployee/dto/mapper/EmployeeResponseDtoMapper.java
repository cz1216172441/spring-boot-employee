package com.thoughtworks.springbootemployee.dto.mapper;

import com.thoughtworks.springbootemployee.dto.EmployeeResponseDto;
import com.thoughtworks.springbootemployee.entity.Employee;

public class EmployeeResponseDtoMapper {

    public static EmployeeResponseDto toEmployeeResponseDto(Employee employee) {
        return new EmployeeResponseDto();
    }
}
