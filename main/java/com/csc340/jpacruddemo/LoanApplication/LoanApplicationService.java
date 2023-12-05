package com.csc340.jpacruddemo.LoanApplication;

import com.csc340.jpacruddemo.user.CustomUserDetails;
import com.csc340.jpacruddemo.user.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.csc340.jpacruddemo.Audit.AuditService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


import java.util.List;

@Service
public class LoanApplicationService {

    @Autowired
    private LoanApplicationRepository repository;

    @Autowired
    private AuditService auditService;

    public List<LoanApplication> getAllLoanApplications() {
        return repository.findAll();
    }

    public LoanApplication getLoanApplicationById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public LoanApplication saveOrUpdateLoanApplication(LoanApplication loanApplication) {
        boolean existsActiveApplication = repository.existsByApplicantIdAndStatusNot(loanApplication.getApplicantId(), "COMPLETED");

        if (existsActiveApplication) {

            throw new RuntimeException("An active loan application already exists for this applicant");
        }

        return repository.save(loanApplication);
    }


    public void deleteLoanApplication(Long id) {
        repository.deleteById(id);
    }

    public void approveLoanApplication(Long id) {
        LoanApplication application = repository.findById(id).orElseThrow(() -> new RuntimeException("Application not found"));
        application.setStatus("APPROVED");
        repository.save(application);


        auditService.logAction(
                getCurrentUserId(),
                "APPROVE",
                "Approved loan application for applicant ID: " + application.getApplicantId(),
                application.getId()
        );
    }

    public void denyLoanApplication(Long id) {
        LoanApplication application = repository.findById(id).orElseThrow(() -> new RuntimeException("Application not found"));
        application.setStatus("DENIED");
        repository.save(application);


        auditService.logAction(
                getCurrentUserId(),
                "DENY",
                "Denied loan application for applicant ID: " + application.getApplicantId(),
                application.getId()
        );
    }


    public LoanApplication submitLoanApplication(LoanApplication loanApplication) {
        LoanApplication savedApplication = repository.save(loanApplication);

        // 记录审计日志
        Long userId = getCurrentUserId();
        String actionDescription = "Submitted loan application for amount: " + loanApplication.getAmount();
        Long affectedEntityId = savedApplication.getId();
        auditService.logAction(userId, "SUBMIT", actionDescription, affectedEntityId);

        return savedApplication;
    }


    private Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            return userDetails.getUserId();
        }
        throw new RuntimeException("User not authenticated");
    }

    public boolean removeLoanApplication(Long id) {
        // 逻辑以移除贷款申请
        // 比如，从数据库中删除记录或将其标记为已移除
        // 这里是一个示例逻辑
        try {
            repository.deleteById(id);
            return true;
        } catch (Exception e) {
            // 处理异常
            return false;
        }
    }

}
