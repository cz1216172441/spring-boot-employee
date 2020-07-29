package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
}
