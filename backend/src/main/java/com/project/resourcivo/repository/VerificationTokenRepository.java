package com.project.resourcivo.repository;

import com.project.resourcivo.model.VerificationToken;
import com.project.resourcivo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

    Optional<VerificationToken> findByToken(String token);

    Optional<VerificationToken> findByUser(User user);

    void deleteByUser(User user);
}
