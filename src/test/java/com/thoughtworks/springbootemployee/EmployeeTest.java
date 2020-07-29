package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EmployeeTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void should_return_a_employee_when_get_employee_given_a_employee() {
        //given
        Integer id = 1;
        Employee employee = new Employee();
        employee.setId(id);
        //when
        employeeRepository = mock(EmployeeRepository.class);
        when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));
        Employee actualEmployee = employeeService.getEmployeeById(id);
        //then
        assertEquals(employee, actualEmployee);
    }
}
