package org.example.business.impl;

import org.example.business.api.Requete;
import org.example.business.api.Visiteur;

import java.util.Set;
import java.util.List;
import java.util.Optional;

public class Union implements Requete {
    private List<Requete> sousRequetes;

    public Union(List<Requete> sousRequetes) {
        assert sousRequetes != null : "La liste des sous-requêtes ne peut pas être nulle";
        assert !sousRequetes.contains(null) : "La liste des sous-requêtes ne peut pas contenir de valeur nulle";
        assert sousRequetes.size() >= 2 : "L'union nécessite au moins deux sous-requêtes";
        this.sousRequetes = sousRequetes;
    }

    @Override
    public Set<Personne> executer() {
        return sousRequetes.stream()
                .flatMap(requete -> requete.executer().stream())
                .collect(java.util.stream.Collectors.toSet());
    }

    @Override
    public Optional<List<Requete>> getSousRequetes() {
        return Optional.of(sousRequetes);
    }

    @Override
    public int getProfondeur() {
        return sousRequetes.stream()
                .mapToInt(Requete::getProfondeur)
                .max()
                .orElse(0) + 1;
    }

    @Override
    public void accepter(Visiteur v) {
        v.visiter(this);
    }

}
