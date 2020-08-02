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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
    void should_return_1_company_with_id_1_when_get_company_by_id_given_1_company_with_id_1() throws Exception {
        //given
        companyRepository.save(new Company(null, "tw"));
        //when
        mockMvc.perform(get("/companies/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("tw"));
    }

    @Test
    void should_return_1_modified_company_when_modify_company_given_1_company_and_1_company_request_dto() throws Exception {
        //given
        companyRepository.save(new Company(null, "tw"));
        String jsonContent = "{\n" +
                "    \"companyId\": 1,\n" +
                "    \"name\": \"tw2\"\n" +
                "}";
        //when
        mockMvc.perform(put("/companies").contentType(MediaType.APPLICATION_JSON).content(jsonContent))
                .andExpect(status().isOk());
        Company company = companyRepository.findById(1).orElse(null);
        //then
        assertNotNull(company);
        assertEquals("tw2", company.getName());
    }

    @Test
    void should_return_companies_and_size_is_0_when_delete_company_by_id_given_1_company_with_company_id_1() throws Exception {
        //given
        companyRepository.save(new Company(null, "tw"));
        //when
        mockMvc.perform(delete("/companies/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        List<Company> companies = companyRepository.findAll();
        //then
        assertEquals(0, companies.size());
    }

    @Test
    void should_return_page_with_content_1_company_when_get_paging_companies_given_companies_2_and_page_1_and_size_1() throws Exception {
        // given
        companyRepository.save(new Company(null, "tw"));
        companyRepository.save(new Company(null, "oocl"));
        // when
        mockMvc.perform(get("/companies?page=1&size=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("totalElements").value(2))
                .andExpect(jsonPath("totalPages").value(2))
                .andExpect(jsonPath("last").value(true))
                .andExpect(jsonPath("size").value(1))
                .andExpect(jsonPath("number").value(1))
                .andExpect(jsonPath("content.[0].name").value("oocl"));
    }
}
