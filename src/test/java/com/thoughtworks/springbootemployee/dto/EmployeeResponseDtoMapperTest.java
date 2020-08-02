package com.thoughtworks.springbootemployee.dto;

import com.thoughtworks.springbootemployee.dto.mapper.EmployeeResponseDtoMapper;
import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeResponseDtoMapperTest {
    @Test
    void should_return_a_employee_response_dto_when_to_employee_response_dto_given_a_employee() {
        // given
        Employee employee = new Employee(1, "abc", 18, "male");
        employee.setCompany(new Company(1, "tw"));
        // when
        EmployeeResponseDto employeeResponseDto = EmployeeResponseDtoMapper.toEmployeeResponseDto(employee);
        // then
        assertAll(
            () -> assertEquals(1, employeeResponseDto.getId()),
            () -> assertEquals("abc", employeeResponseDto.getName()),
            () -> assertEquals(18, employeeResponseDto.getAge()),
            () -> assertEquals("male", employeeResponseDto.getGender()),
            () -> assertEquals("tw", employeeResponseDto.getCompanyName())
        );
    }
}
