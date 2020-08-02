package com.thoughtworks.springbootemployee.dto;

import com.thoughtworks.springbootemployee.dto.mapper.EmployeeRequestDtoMapper;
import com.thoughtworks.springbootemployee.entity.Employee;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeRequestDtoMapperTest {
    @Test
    void should_return_1_employee_when_to_entity_given_1_employee_dto() {
        //given
        EmployeeRequestDto employeeRequestDto = new EmployeeRequestDto(1, "abc", 18, "male", 1);
        //when
        Employee employee = EmployeeRequestDtoMapper.toEntity(employeeRequestDto);
        //then
        assertAll(
            () -> assertEquals(1, employee.getId()),
            () -> assertEquals("abc", employee.getName()),
            () -> assertEquals(18, employee.getAge()),
            () -> assertEquals("male", employee.getGender()),
            () -> assertNull(employee.getCompany())
        );
    }
}
