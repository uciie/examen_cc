package org.example.business.impl;

import org.example.business.api.Requete;
import org.example.business.api.Visiteur;

import java.util.Set;
import java.util.List;
import java.util.Optional;

public class Difference implements Requete {
    private Requete requeteA;
    private Requete requeteB;

    public Difference(Requete requeteA, Requete requeteB) {
        assert requeteA != null : "La requête A ne peut pas être nulle";
        assert requeteB != null : "La requête B ne peut pas être nulle";

        this.requeteA = requeteA;
        this.requeteB = requeteB;
    }

    @Override
    public Set<Personne> executer() {
        Set<Personne> resultatA = requeteA.executer();
        Set<Personne> resultatB = requeteB.executer();
        resultatA.removeAll(resultatB);
        return resultatA;
    }

    @Override
    public Optional<List<Requete>> getSousRequetes() {
        return Optional.of(List.of(requeteA, requeteB));
    }

    @Override
    public int getProfondeur() {
        return 1+ Math.max(requeteA.getProfondeur(), requeteB.getProfondeur());
    }

    @Override
    public void accepter(Visiteur v) {
        v.visiter(this);
    }
    
}
