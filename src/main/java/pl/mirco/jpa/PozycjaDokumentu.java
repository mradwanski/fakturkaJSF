/*
 * Copyright (C) 2014 mradwanski
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package pl.mirco.jpa;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author mradwanski
 */
@Entity
public class PozycjaDokumentu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    @NotNull
    @ManyToOne
    private Dokument dokument;
    
    @NotNull
    private Integer lp;
    @Size(min = 2, max = 128)
    @NotNull
    private String nazwa;
    @Size(max = 16)
    private String pkwiu;
    @NotNull
    private Double ilosc;
    @ManyToOne
    private JednostkaMiary jednostkaMiary;
    @NotNull
    @Column(precision=10, scale=2)
    private BigDecimal cenaNetto;
    @NotNull
    @OneToOne
    private StawkaVAT stawkaVAT;
    @Column(precision=10, scale=2)
    private BigDecimal wartoscNetto;
    @Column(precision=10, scale=2)
    private BigDecimal wartoscVAT;
    @Column(precision=10, scale=2)
    private BigDecimal wartoscBrutto;


    public Dokument getDokument() {
        return dokument;
    }

    public void setDokument(Dokument dokument) {
        this.dokument = dokument;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getPkwiu() {
        return pkwiu;
    }

    public void setPkwiu(String pkwiu) {
        this.pkwiu = pkwiu;
    }

    public Double getIlosc() {
        return ilosc;
    }

    public void setIlosc(Double ilosc) {
        this.ilosc = ilosc;
    }

    public JednostkaMiary getJednostkaMiary() {
        return jednostkaMiary;
    }

    public void setJednostkaMiary(JednostkaMiary jednostkaMiary) {
        this.jednostkaMiary = jednostkaMiary;
    }

    public BigDecimal getCenaNetto() {
        if (cenaNetto != null) {
            return cenaNetto.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        return cenaNetto;
    }

    public void setCenaNetto(BigDecimal cenaNetto) {
        if (cenaNetto != null) {
            this.cenaNetto = cenaNetto.setScale(2, BigDecimal.ROUND_HALF_UP);
        } else {
            this.cenaNetto = cenaNetto;
        }

    }

    public StawkaVAT getStawkaVAT() {
        return stawkaVAT;
    }

    public void setStawkaVAT(StawkaVAT stawkaVAT) {
        this.stawkaVAT = stawkaVAT;
    }

    public BigDecimal getWartoscVAT() {
        if (getWartoscNetto() != null && getStawkaVAT() != null) {
            wartoscVAT = getWartoscNetto().multiply(getStawkaVAT().getWartosc()).setScale(2, BigDecimal.ROUND_HALF_UP);
        } else {
            wartoscVAT = null;
        }

        return wartoscVAT;
    }

    public void setWartoscVAT(BigDecimal wartoscVAT) {
        if (wartoscVAT != null) {
            this.wartoscVAT = wartoscVAT.setScale(2, BigDecimal.ROUND_HALF_UP);
        } else {
            this.wartoscVAT = wartoscVAT;
        }

    }

    public BigDecimal getWartoscNetto() {
        if (getCenaNetto() != null && getIlosc() != null) {
            wartoscNetto = getCenaNetto().multiply(new BigDecimal(getIlosc())).setScale(2, BigDecimal.ROUND_HALF_UP);;
        } else {
            wartoscNetto = null;
        }

        return wartoscNetto;
    }

    public void setWartoscNetto(BigDecimal wartoscNetto) {
        if (wartoscNetto != null) {
            this.wartoscNetto = wartoscNetto.setScale(2, BigDecimal.ROUND_HALF_UP);
        } else {
            this.wartoscNetto = wartoscNetto;
        }

    }

    public BigDecimal getWartoscBrutto() {
        if (getWartoscNetto() != null && getWartoscVAT() != null) {
            wartoscBrutto = getWartoscNetto().add(getWartoscVAT()).setScale(2, BigDecimal.ROUND_HALF_UP);
        } else {
            wartoscBrutto = null;
        }

        return wartoscBrutto;
    }

    public void setWartoscBrutto(BigDecimal wartoscBrutto) {
        if (wartoscBrutto != null) {
            this.wartoscBrutto = wartoscBrutto.setScale(2, BigDecimal.ROUND_HALF_UP);
        } else {
            this.wartoscBrutto = wartoscBrutto;
        }

    }

    public Integer getLp() {
        return lp;
    }

    public void setLp(Integer lp) {
        this.lp = lp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PozycjaDokumentu)) {
            return false;
        }
        PozycjaDokumentu other = (PozycjaDokumentu) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "pl.mirco.model.PozycjaDokumentu[ id=" + id + " ]";
    }

}
