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
public class CompanyIntegrationTest {

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
    void should_return_1_company_when_add_company_given_1_company_request_dto() throws Exception {
        //given
        String jsonContent = "{\n" +
                "    \"name\": \"tw\"\n" +
                "}";
        //when
        mockMvc.perform(post("/companies").contentType(MediaType.APPLICATION_JSON).content(jsonContent))
                .andExpect(status().isOk());
        List<Company> companies = companyRepository.findAll();
        // then
        assertEquals(1, companies.size());
    }

    @Test
    void should_return_2_company_when_get_all_companies_given_2_company() throws Exception {
        //given
        companyRepository.save(new Company(null, "tw"));
        companyRepository.save(new Company(null, "oocl"));
        //when
        //then
        mockMvc.perform(get("/companies").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].name").value("tw"))
                .andExpect(jsonPath("[1].name").value("oocl"));
    }

    @Test
    void should_return_1_company_with_id_1_when_get_companies_by_id_given_1_company_with_id_1() throws Exception {
        //given
        companyRepository.save(new Company(null, "tw"));
        //when
        mockMvc.perform(get("/companies/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("tw"));
    }
}
