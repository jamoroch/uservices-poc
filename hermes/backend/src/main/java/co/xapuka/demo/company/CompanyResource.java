package co.xapuka.demo.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
public class CompanyResource {

    @Autowired
    private CompanyService companyService;

    @GetMapping(value = "/")
    public ResponseEntity getAll() {
        return ResponseEntity.ok(companyService.getAll());
    }

    @GetMapping(value = "/{companyName}")
    public ResponseEntity get(@PathVariable("companyName")  String companyName) {
        Company company = companyService.get(companyName);

        if(company == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(company);
        }
    }

    @PostMapping(value="/")
    public ResponseEntity create(@RequestBody Map<String, String> data) {
        String tags  = data.get("tags");
        return ResponseEntity.ok(companyService.create(data.get("companyName"), tags));
    }

    @PutMapping(value="/{companyId}/{tags}")
    public ResponseEntity update(@PathVariable("companyId")UUID companyId, @PathVariable("tags")String tags){
        return ResponseEntity.ok(companyService.update(companyId, tags));
    }

}
