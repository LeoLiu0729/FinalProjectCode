package com.csc340.jpacruddemo.Banker;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class BankerController {
    private final BankerService clientService;

    @Autowired
    public BankerController(BankerService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/getAll")
    public List<Banker> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/getByAccountID/{accountID}")
    public ResponseEntity<Banker> getClientByAccountID(@PathVariable Long accountID) {
        return clientService.getClientByAccountID(accountID);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createClient(@RequestBody @Valid Banker client) {
        return clientService.createClient(client);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Banker> updateClient(@PathVariable Long id, @RequestBody Banker updatedClient) {
        return clientService.updateClient(id, updatedClient);
    }

    @Transactional
    @DeleteMapping("/close/{accountID}")
    public ResponseEntity<Void> closeClientAccount(@PathVariable Long accountID) {
        return clientService.closeClientAccount(accountID);
    }
}