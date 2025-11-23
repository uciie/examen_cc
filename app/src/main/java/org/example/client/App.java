package org.example.client;

import org.example.business.api.Requete;
import org.example.business.impl.visiteurs.CoutVisiteur;
import org.example.business.impl.Personne;
import org.example.business.impl.Population;
import org.example.business.impl.Selection;
import org.example.business.impl.Difference;
import org.example.business.impl.Union;

import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.function.Predicate;

public class App {

    static void printResultatQ1(Requete requete) {
        String reponse = String.format("%s(%d)", requete.executer(), requete.getProfondeur());
        System.out.println(reponse);
    }

    static void printResultatQ2(Requete requete) {
        String reponse = String.format("%s(%d)", requete.executer(), requete.getProfondeur());
        System.out.println(reponse);

        CoutVisiteur coutVisiteur = new CoutVisiteur();
        requete.accepter(coutVisiteur);
        System.out.println(coutVisiteur.getCout());
    }

    static Population creerPopulationDemo() {
        Set<Personne> personnes = new HashSet<>();
        personnes.add(new Personne("alice", 20, 2000));
        personnes.add(new Personne("bob", 17, 100));
        personnes.add(new Personne("charlie", 17, 2600));
        personnes.add(new Personne("david", 70, 1000));
        personnes.add(new Personne("elisabeth", 72, 900));
        return new Population(personnes);
    }

    static Predicate<Personne> mineur() {
        return personne -> personne.age() < 18;
    }

    static Predicate<Personne> retraite() {
        return personne -> personne.age() >= 64;
    }

    static Predicate<Personne> riche() {
        return personne -> personne.salaire() >= 2500;
    }

    static Predicate<Personne> pauvre() {
        return personne -> personne.salaire() < 1000;
    }

    public static void main(String[] args) {
        /* ========== QUESTION 1 ========== */
        // ensemble des 5 personnes
        Population population = creerPopulationDemo();
        printResultatQ1(population);

        // les mineurs
        Selection selectionMineurs = new Selection(population, mineur());
        printResultatQ1(selectionMineurs);

        // les retraités
        Selection selectionRetraites = new Selection(population, retraite());
        printResultatQ1(selectionRetraites);
        
        // les mineurs riches
        Selection mineursRiches = new Selection(selectionMineurs, riche());
        printResultatQ1(mineursRiches);

        // les pauvres retraités
        Selection PauvresRetraites = new Selection(selectionRetraites, pauvre());
        printResultatQ1(PauvresRetraites);

        // union des mineurs riches et des pauvres retraités
        Union union = new Union(List.of(mineursRiches, PauvresRetraites));
        printResultatQ1(union);
        
        System.out.println("-------------------------");

        /* ========== QUESTION 2 ========== */
        System.out.println("Couts des requetes:");
        printResultatQ2(population);
        printResultatQ2(selectionMineurs);
        printResultatQ2(selectionRetraites);
        printResultatQ2(mineursRiches);
        printResultatQ2(PauvresRetraites);
        printResultatQ2(union);
    }
}