package com.csc340.jpacruddemo.Banker;

import com.csc340.jpacruddemo.user.User;
import com.csc340.jpacruddemo.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

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

        // Set initial balance and generate a temporary password for the client
        client.setBalance(0.0);

        // Create a User entity with the necessary information
        User user = new User();
        user.setEmail(client.getEmail()); // Assuming email is unique in the User table
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER"); // Set the role to USER
        // Set other user properties as needed

        // Save the User entity
        User savedUser = userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
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
}