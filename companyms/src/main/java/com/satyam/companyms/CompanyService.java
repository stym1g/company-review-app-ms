package com.satyam.companyms;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies();
    Company getCompanyById(Long id);
    Boolean updateCompany(Long id, Company company);
    void createCompany(Company company);
    Boolean deleteCompany(Long id);
}
