package com.example.iwa_ms_admin_moderation.services;

import com.example.iwa_ms_admin_moderation.models.Flags;
import com.example.iwa_ms_admin_moderation.repositories.FlagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class FlagService {

    private final FlagRepository flagRepository;

    @Autowired
    public FlagService(FlagRepository flagRepository) {
        this.flagRepository = flagRepository;
    }

    // Créer un nouveau flag
    public Flags createFlag(Flags flag) {
        return flagRepository.save(flag);
    }

    // Récupérer tous les flags
    public List<Flags> getAllFlags() {
        return flagRepository.findAll();
    }

    // Récupérer un flag par ID
    public Optional<Flags> getFlagById(Integer flagId) {
        return flagRepository.findById(flagId);
    }

    // Mettre à jour un flag
    public Flags updateFlag(Flags flag) {
        return flagRepository.save(flag);
    }

    // Supprimer un flag par ID
    public void deleteFlag(Integer flagId) {
        flagRepository.deleteById(flagId);
    }

    // Récupérer les flags par statut
    public List<Flags> getFlagsByStatus(String status) {
        return flagRepository.findByStatus(status);
    }
}
