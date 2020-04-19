package com.dummy.myerp.model.bean.comptabilite;


import javax.validation.constraints.NotNull;

/**
 * Bean représentant une séquence pour les références d'écriture comptable
 */
public class SequenceEcritureComptable {

    // ==================== Attributs ====================
    /**
     * L'année
     */
    private Integer annee;
    /**
     * La dernière valeur utilisée
     */
    private Integer derniereValeur;

    @NotNull
    private JournalComptable journal;

    // ==================== Constructeurs ====================

    /**
     * Constructeur
     */
    public SequenceEcritureComptable() {
    }

    /**
     * Constructeur
     *
     * @param pAnnee          -
     * @param pDerniereValeur -
     */
    public SequenceEcritureComptable(Integer pAnnee, JournalComptable journalComptable, Integer pDerniereValeur) {
        annee = pAnnee;
        journal = journalComptable;
        derniereValeur = pDerniereValeur;
    }


    // ==================== Getters/Setters ====================
    public JournalComptable getJournal() {
        return journal;
    }

    public void setJournal(JournalComptable pJournal) {
        journal = pJournal;
    }

    public Integer getAnnee() {
        return annee;
    }

    public void setAnnee(Integer pAnnee) {
        annee = pAnnee;
    }

    public Integer getDerniereValeur() {
        return derniereValeur;
    }

    public void setDerniereValeur(Integer pDerniereValeur) {
        derniereValeur = pDerniereValeur;
    }


    // ==================== Méthodes ====================
    @Override
    public String toString() {
        final StringBuilder vStB = new StringBuilder(this.getClass().getSimpleName());
        final String vSEP = ", ";
        vStB.append("{")
                .append("annee=").append(annee)
                .append(vSEP).append("derniereValeur=").append(derniereValeur)
                .append("}");
        return vStB.toString();
    }
}
