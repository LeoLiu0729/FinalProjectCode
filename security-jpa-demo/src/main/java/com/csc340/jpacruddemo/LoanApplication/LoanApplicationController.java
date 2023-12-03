package com.csc340.jpacruddemo.LoanApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;


import java.util.List;

@RestController
@RequestMapping("/loan-applications")
public class LoanApplicationController {

    @Autowired
    private LoanApplicationService service;

    @GetMapping
    public List<LoanApplication> getAllLoanApplications() {
        return service.getAllLoanApplications();
    }

    @GetMapping("/{id}")
    public LoanApplication getLoanApplicationById(@PathVariable Long id) {
        return service.getLoanApplicationById(id);
    }

    @PostMapping
    public ResponseEntity<?> createOrUpdateLoanApplication(@RequestBody LoanApplication loanApplication) {
        try {
            LoanApplication savedApplication = service.saveOrUpdateLoanApplication(loanApplication);
            return ResponseEntity.ok(savedApplication);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public void deleteLoanApplication(@PathVariable Long id) {
        service.deleteLoanApplication(id);
    }

    @PostMapping("/{id}/approve")
    public ResponseEntity<?> approveApplication(@PathVariable Long id) {
        service.approveLoanApplication(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/deny")
    public ResponseEntity<?> denyApplication(@PathVariable Long id) {
        service.denyLoanApplication(id);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/{id}/remove")
    public ResponseEntity<?> removeLoanApplication(@PathVariable Long id) {
        boolean removed = service.removeLoanApplication(id);
        if (removed) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
