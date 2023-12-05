package com.csc340.jpacruddemo.Banker;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
@Validated
public class BankerService {

    private final BankerRepository clientRepository;

    @Autowired
    public BankerService(BankerRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Banker> getAllClients() {
        return clientRepository.findAll();
    }

    public ResponseEntity<Banker> getClientByAccountID(Long accountID) {
        Optional<Banker> client = clientRepository.findByAccountID(accountID);
        return client.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new client
    public ResponseEntity<?> createClient(Banker client) {

        // Check if email already exists
        if (clientRepository.existsByEmail(client.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("An account with this email already exists");
        }

        // Set initial balance and generate a temporary password for client
        client.setBalance(0.0);
        client.setTemporaryPassword(generateTemporaryPassword(client.getLastName(), client.getFirstName()));

        // Save the client using the repository
        Banker createdClient = clientRepository.save(client);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdClient);
    }

    public ResponseEntity<Banker> updateClient(Long id, Banker updatedClient) {
        if (clientRepository.existsById(id)) {
            updatedClient.setAccountID(id);
            Banker savedClient = clientRepository.save(updatedClient);
            return ResponseEntity.ok(savedClient);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Close a clients account
    @Transactional
    public ResponseEntity<Void> closeClientAccount(Long accountID) {
        if (clientRepository.existsByAccountID(accountID)) {
            clientRepository.deleteByAccountID(accountID);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private String generateTemporaryPassword(String lastName, String firstName) {
        String basePassword = lastName + firstName;
        int randomNumber = (int) (Math.random() * 1000);
        return basePassword + randomNumber;
    }
}