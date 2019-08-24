package co.xapuka.demo.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public Company get(UUID companyId) {
        Optional<Company> company = Optional.ofNullable(companyRepository.getOne(companyId));
        company.ifPresent(c -> System.out.println(c));
        return company.orElse(null);
    }

    public List<Company> getAll() {
        return companyRepository.findAll();
    }

    public Company get(String companyName) {
        Optional<Company> company = companyRepository.findByCompanyName(companyName);
        company.ifPresent(c -> System.out.println(c));
        return company.orElse(null);
    }

    @Transactional
    public Company create(String companyName, String... tags  ) {
        return companyRepository.findByCompanyName(companyName).orElse(companyRepository.save(doCreate(companyName,
                tags)));
    }

    private Company doCreate(String companyName, String...tags) {
        Company company = new Company();
        company.setCompanyName(companyName);
        if(tags != null && tags.length > 0) {
            company.setTags(tags[0]);
        }
        return company;
    }

    @Transactional
    public Company update(UUID companyId, String tags) {
        Company company = companyRepository.getOne(companyId);
        company.setTags(tags);
        return companyRepository.save(company);
    }

}
