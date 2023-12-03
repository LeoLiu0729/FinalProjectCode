package com.csc340.jpacruddemo.Audit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/audits")
public class AuditWebController {

    @Autowired
    private AuditService auditService;

    @GetMapping
    public String listAudits(Model model) {
        model.addAttribute("audits", auditService.getAllAudits());
        return "audits";
    }
}