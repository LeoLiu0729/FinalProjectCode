package com.csc340.jpacruddemo.user;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.csc340.jpacruddemo.Audit.AuditService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;


@Service
public class UserService {

    @Autowired
    UserRepository repo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuditService auditService;

    /**
     * Get all users
     *
     * @return the list of users.
     */
    public List<User> getAllUsers() {
        return repo.findAll();
    }

    /**
     * Find one user by ID.
     *
     * @param id
     * @return the user
     */
    public User getUser(long id) {
        return repo.getReferenceById(id);
    }

    /**
     * Delete user by ID.
     *
     * @param id
     */
    public void deleteUser(long id) {
        User user = repo.findById(id).orElseThrow(() ->
                new UsernameNotFoundException("User not found with id: " + id));
        repo.deleteById(id);
        Long currentUserId = getCurrentUserId();
        auditService.logAction(
                currentUserId,
                "DELETE",
                "Deleted user with username: " + user.getUserName(),
                user.getId()
        );
    }
    public void saveUser(User user) {
        boolean isNew = user.getId() == 0;
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repo.save(user);
        Long currentUserId = getCurrentUserId();
        String action = isNew ? "ADD" : "UPDATE";
        auditService.logAction(
                currentUserId,
                action,
                action + " user with username: " + user.getUserName(),
                user.getId()
        );
    }
    private Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            if (authentication.getPrincipal() instanceof CustomUserDetails) {
                CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
                return userDetails.getUserId();
            }
        }
        throw new RuntimeException("Unable to get current user ID");
    }
    public void updateUser(User user) {
        User existing = repo.getReferenceById(user.getId());

        StringBuilder changes = new StringBuilder();

        if (user.getUserName() != null && !user.getUserName().equals(existing.getUserName())) {
            changes.append("Username changed from ").append(existing.getUserName()).append(" to ").append(user.getUserName()).append("; ");
            existing.setUserName(user.getUserName());
        }

        if (user.getPassword() != null && !passwordEncoder.matches(user.getPassword(), existing.getPassword())) {
            changes.append("Password changed; ");
            existing.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        if (user.getEmail() != null && !user.getEmail().equals(existing.getEmail())) {
            changes.append("Email changed from ").append(existing.getEmail()).append(" to ").append(user.getEmail()).append("; ");
            existing.setEmail(user.getEmail());
        }

        if (user.getRole() != null && !user.getRole().equals(existing.getRole())) {
            changes.append("Role changed from ").append(existing.getRole()).append(" to ").append(user.getRole()).append("; ");
            existing.setRole(user.getRole());
        }
        repo.save(existing);

        if (!changes.isEmpty()) {
            Long currentUserId = getCurrentUserId();
            auditService.logAction(
                    currentUserId,
                    "UPDATE",
                    "Updated user with ID: " + existing.getId() + "; " + changes,
                    existing.getId()
            );
        }
    }
}