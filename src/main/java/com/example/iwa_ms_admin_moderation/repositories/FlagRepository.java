package com.example.iwa_ms_admin_moderation.repositories;

import com.example.iwa_ms_admin_moderation.models.Flags;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FlagRepository extends JpaRepository<Flags, Integer> {
    // Méthode pour trouver les flags par statut
    List<Flags> findByStatus(String status);
}
