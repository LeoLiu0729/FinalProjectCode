package com.csc340.jpacruddemo.Audit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AuditService {

    @Autowired
    private AuditRepository auditRepository;


    public void logAction(Long userId, String actionType, String actionDescription, Long affectedEntityId) {
        Audit audit = new Audit(userId, actionType, actionDescription, affectedEntityId);
        auditRepository.save(audit);
    }

    public List<Audit> getAllAudits() {
        return auditRepository.findAll();
    }


}
