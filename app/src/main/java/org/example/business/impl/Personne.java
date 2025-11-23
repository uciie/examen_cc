package org.example.business.impl;

public record Personne(String nom, int age, int salaire) {
    
    @Override
    public String toString() {
        return "(" + nom + ", " + age + ", " + salaire + ")";
    }
}
