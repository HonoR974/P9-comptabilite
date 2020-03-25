package com.dummy.myerp.model.bean.comptabilite;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Bean représentant une Écriture Comptable
 */
public class EcritureComptable {
    private static final Logger log = LoggerFactory.getLogger(EcritureComptable.class);

    // ==================== Attributs ====================
    /**
     * La liste des lignes d'écriture comptable.
     */
    @Valid
    @Size(min = 2)
    private final List<LigneEcritureComptable> listLigneEcriture = new ArrayList<>();
    /**
     * The Id.
     */
    private Integer id;
    /**
     * Journal comptable
     */
    @NotNull
    private JournalComptable journal;
    /**
     * The Reference.
     */
    @Pattern(regexp = "\\d{1,5}-\\d{4}/\\d{5}")
    private String reference;
    /**
     * The Date.
     */
    @NotNull
    private Date date;
    /**
     * The Libelle.
     */
    @NotNull
    @Size(min = 1, max = 200)
    private String libelle;

    // ==================== Getters/Setters ====================
    public Integer getId() {
        return id;
    }

    public void setId(Integer pId) {
        id = pId;
    }

    public JournalComptable getJournal() {
        return journal;
    }

    public void setJournal(JournalComptable pJournal) {
        journal = pJournal;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String pReference) {
        reference = pReference;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date pDate) {
        date = pDate;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String pLibelle) {
        libelle = pLibelle;
    }

    public List<LigneEcritureComptable> getListLigneEcriture() {
        return listLigneEcriture;
    }

    /**
     * Calcul et renvoie le total des montants au débit des lignes d'écriture
     *
     * @return {@link BigDecimal}, {@link BigDecimal#ZERO} si aucun montant au débit
     */
    public BigDecimal getTotalDebit() {
        BigDecimal vRetour = BigDecimal.ZERO;
        for (LigneEcritureComptable vLigneEcritureComptable : listLigneEcriture) {
            if (vLigneEcritureComptable.getDebit() != null) {
                vRetour = vRetour.add(vLigneEcritureComptable.getDebit());
            }
        }
        return vRetour.setScale(2, RoundingMode.FLOOR);
    }

    /**
     * Calcul et renvoie le total des montants au crédit des lignes d'écriture
     *
     * @return {@link BigDecimal}, {@link BigDecimal#ZERO} si aucun montant au crédit
     */
    public BigDecimal getTotalCredit() {
        BigDecimal vRetour = BigDecimal.ZERO;
        for (LigneEcritureComptable vLigneEcritureComptable : listLigneEcriture) {
            if (vLigneEcritureComptable.getCredit() != null) {
                vRetour = vRetour.add(vLigneEcritureComptable.getCredit());
            }
        }
        return vRetour.setScale(2, RoundingMode.FLOOR);
    }

    /**
     * Renvoie si l'écriture est équilibrée (TotalDebit = TotalCrédit)
     *
     * @return boolean
     */
    public boolean isEquilibree() {
        return this.getTotalDebit().equals(getTotalCredit());
    }

    // ==================== Méthodes ====================
    @Override
    public String toString() {
        final StringBuilder vStB = new StringBuilder(this.getClass().getSimpleName());
        final String vSEP = ", ";
        vStB.append("{")
                .append("id=").append(id)
                .append(vSEP).append("journal=").append(journal)
                .append(vSEP).append("reference='").append(reference).append('\'')
                .append(vSEP).append("date=").append(date)
                .append(vSEP).append("libelle='").append(libelle).append('\'')
                .append(vSEP).append("totalDebit=").append(this.getTotalDebit().toPlainString())
                .append(vSEP).append("totalCredit=").append(this.getTotalCredit().toPlainString())
                .append(vSEP).append("listLigneEcriture=[\n")
                .append(StringUtils.join(listLigneEcriture, "\n")).append("\n]")
                .append("}");
        return vStB.toString();
    }
}
