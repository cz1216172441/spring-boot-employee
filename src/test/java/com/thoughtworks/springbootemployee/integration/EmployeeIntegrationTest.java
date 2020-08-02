package com.thoughtworks.springbootemployee.integration;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeIntegrationTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private MockMvc mockMvc;

    @AfterEach
    public void tearDown() {
        employeeRepository.deleteAll();
        companyRepository.deleteAll();
    }

    @Test
    void should_return_1_employee_when_add_employee_given_1_employee_request_dto() throws Exception {
        //given
        companyRepository.save(new Company(null, "tw"));
        String jsonContent = "{\n" +
                "    \"name\": \"dong\",\n" +
                "    \"age\": 108,\n" +
                "    \"gender\": \"female\",\n" +
                "    \"companyId\": 1\n" +
                "}";
        mockMvc.perform(post("/employees").contentType(MediaType.APPLICATION_JSON).content(jsonContent))
                .andExpect(status().isOk());
        //when
        List<Employee> employees = employeeRepository.findAll();
        //then
        assertEquals(1, employees.size());
    }

    @Test
    void should_return_2_employees_when_get_all_employees_given_2_employees_and_1_company() throws Exception {
        //given
        Company company = companyRepository.save(new Company(null, "tw"));
        Employee employee1 = new Employee(null, "roy", 18, "male");
        employee1.setCompany(company);
        Employee employee2 = new Employee(null, "max", 18, "male");
        employee2.setCompany(company);
        employeeRepository.save(employee1);
        employeeRepository.save(employee2);
        //when
        //then
        mockMvc.perform(get("/employees").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].name").value("roy"))
                .andExpect(jsonPath("[1].name").value("max"));
    }

    @Test
    void should_return_1_employee_with_id_1_when_get_employee_by_id_given_employee_id_1() throws Exception {
        //given
        Company company = companyRepository.save(new Company(null, "tw"));
        Employee employee = new Employee(null, "chengcheng", 18, "male");
        employee.setCompany(company);
        employeeRepository.save(employee);
        //when
        //then
        mockMvc.perform(get("/employees/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("chengcheng"));
    }

    @Test
    void should_return_1_employee_with_gender_male_when_get_employee_by_gender_given_a_male_employee_and_a_female_employee() throws Exception {
        //given
        Company company = companyRepository.save(new Company(null, "tw"));
        Employee employee1 = new Employee(null, "roy", 18, "male");
        employee1.setCompany(company);
        Employee employee2 = new Employee(null, "ang", 18, "female");
        employee2.setCompany(company);
        employeeRepository.save(employee1);
        employeeRepository.save(employee2);
        //when
        mockMvc.perform(get("/employees?gender=male").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        List<Employee> employees = employeeRepository.findByGender("male");
        //then
        assertEquals(1, employees.size());
    }

    @Test
    void should_return_page_with_content_1_employee_when_get_paging_employees_given_employee_2_and_page_1_and_size_1() throws Exception {
        //given
        Company company = companyRepository.save(new Company(null, "tw"));
        Employee employee1 = new Employee(null, "roy", 18, "male");
        employee1.setCompany(company);
        Employee employee2 = new Employee(null, "ang", 18, "female");
        employee2.setCompany(company);
        employeeRepository.save(employee1);
        employeeRepository.save(employee2);
        //when
        mockMvc.perform(get("/employees?page=1&size=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("totalElements").value(2))
                .andExpect(jsonPath("totalPages").value(2))
                .andExpect(jsonPath("last").value(true))
                .andExpect(jsonPath("size").value(1))
                .andExpect(jsonPath("number").value(1))
                .andExpect(jsonPath("content.[0].name").value("ang"));
    }
}
