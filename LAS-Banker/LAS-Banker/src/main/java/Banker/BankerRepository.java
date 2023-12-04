package Banker;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankerRepository extends JpaRepository<Banker, Long> {
    Banker save(Banker client);
    Optional<Banker> findByAccountID(Long accountID);
    boolean existsByAccountID(Long accountID);
    boolean existsByEmail(String email);
    void deleteByAccountID(Long accountID);
}