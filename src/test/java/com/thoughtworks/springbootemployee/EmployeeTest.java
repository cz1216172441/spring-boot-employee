package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.exception.EmployeeNotFoundException;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    void should_return_a_employee_when_get_employee_given_a_employee() throws EmployeeNotFoundException {
        // given
        Integer id = 1;
        Employee employee = new Employee();
        employee.setId(id);
        // when
        when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));
        Employee actualEmployee = employeeService.getEmployeeById(id);
        // then
        assertEquals(employee, actualEmployee);
    }

    @Test
    void should_return_exception_when_get_employee_given_a_not_existed_employee_id() {
        // given
        Integer id = 2;
        // when
        when(employeeRepository.findById(id)).thenReturn(Optional.empty());
        // then
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.getEmployeeById(id));
    }



}
