package org.example.business.impl;

import org.example.business.api.Requete;
import org.example.business.api.Visiteur;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public class Population implements Requete {
    
    private Set<Personne> personnes;

    public Population(Set<Personne> personnes) {
        assert personnes != null : "La collection de personnes ne peut pas être nulle";
        this.personnes = new HashSet<>(personnes);
    }

    @Override
    public Set<Personne> executer() {
        return new HashSet<>(personnes);
    }

    @Override
    public Optional<List<Requete>> getSousRequetes() {
        return Optional.empty();
    }

    @Override
    public int getProfondeur() {
        return 1;
    }

    @Override
    public void accepter(Visiteur v) {
        v.visiter(this);
    }
}
