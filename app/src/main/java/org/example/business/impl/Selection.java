package org.example.business.impl;

import org.example.business.api.Requete;
import org.example.business.api.Visiteur;
import java.util.Set;
import java.util.List;
import java.util.function.Predicate;
import java.util.Optional;

public class Selection implements Requete {
    private Requete requeteSource;
    private Predicate<Personne> critereSelection;

    public Selection(Requete requeteSource, Predicate<Personne> critereSelection) {
        assert requeteSource != null : "La requête source ne peut pas être nulle";
        assert critereSelection != null : "Le critère de sélection ne peut pas être nul";
        this.requeteSource = requeteSource;
        this.critereSelection = critereSelection;
    }

    @Override
    public Set<Personne> executer() {
        Set<Personne> resultatSource = requeteSource.executer();
        return resultatSource.stream()
                .filter(critereSelection)
                .collect(java.util.stream.Collectors.toSet());
    }

    @Override
    public Optional<List<Requete>> getSousRequetes() {
        return Optional.of(List.of(requeteSource));
    }

    @Override
    public int getProfondeur() {
        return 1 + requeteSource.getProfondeur();
    }

    @Override
    public void accepter(Visiteur v) {
        v.visiter(this);
    }
}
