package org.example.business.api;

import org.example.business.impl.Personne;

import java.util.List;
import java.util.Set;
import java.util.Optional;

public interface Requete {
    Optional<List<Requete>> getSousRequetes(); // Renvoie une liste vide si pas de sous-requêtes
    int getProfondeur();
    Set<Personne> executer();
    void accepter(Visiteur v);
}
