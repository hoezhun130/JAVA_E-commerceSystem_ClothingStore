/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ILLEGEAR
 */
@Entity
@Table(name = "PURCHASEDRECORD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Purchasedrecord.findAll", query = "SELECT p FROM Purchasedrecord p"),
    @NamedQuery(name = "Purchasedrecord.findByRecordid", query = "SELECT p FROM Purchasedrecord p WHERE p.recordid = :recordid"),
    @NamedQuery(name = "Purchasedrecord.findByCustomerid", query = "SELECT p FROM Purchasedrecord p WHERE p.customerid = :customerid"),
    @NamedQuery(name = "Purchasedrecord.findByTotalpayment", query = "SELECT p FROM Purchasedrecord p WHERE p.totalpayment = :totalpayment"),
    @NamedQuery(name = "Purchasedrecord.findByPurchaseditems", query = "SELECT p FROM Purchasedrecord p WHERE p.purchaseditems = :purchaseditems"),
    @NamedQuery(name = "Purchasedrecord.findByDeliverystatus", query = "SELECT p FROM Purchasedrecord p WHERE p.deliverystatus = :deliverystatus")})
public class Purchasedrecord implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "RECORDID")
    private Integer recordid;
    @Column(name = "CUSTOMERID")
    private Integer customerid;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "TOTALPAYMENT")
    private Double totalpayment;
    @Size(max = 255)
    @Column(name = "PURCHASEDITEMS")
    private String purchaseditems;
    @Size(max = 255)
    @Column(name = "DELIVERYSTATUS")
    private String deliverystatus;
    private List<Purchasedrecord> purchase;

    public Purchasedrecord() {
    }

    public Purchasedrecord(Integer recordid, Integer customerid, Double totalpayment, String purchaseditems, String deliverystatus) {
        this.recordid = recordid;
        this.customerid = customerid;
        this.totalpayment = totalpayment;
        this.purchaseditems = purchaseditems;
        this.deliverystatus = deliverystatus;
    }
    
    public Purchasedrecord(Integer customerid, Double totalpayment, String purchaseditems) {
        this.customerid = customerid;
        this.totalpayment = totalpayment;
        this.purchaseditems = purchaseditems;
    }
    public Purchasedrecord(Integer recordid, Integer customerid, Double totalpayment, String deliverystatus) {
        this.recordid = recordid;
        this.customerid = customerid;
        this.totalpayment = totalpayment;
        this.deliverystatus = deliverystatus;
    }
    
    public Purchasedrecord(Integer recordid) {
        this.recordid = recordid;
    }

    public Integer getRecordid() {
        return recordid;
    }

    public void setRecordid(Integer recordid) {
        this.recordid = recordid;
    }

    public Integer getCustomerid() {
        return customerid;
    }

    public void setCustomerid(Integer customerid) {
        this.customerid = customerid;
    }

    public Double getTotalpayment() {
        return totalpayment;
    }

    public void setTotalpayment(Double totalpayment) {
        this.totalpayment = totalpayment;
    }

    public String getPurchaseditems() {
        return purchaseditems;
    }

    public void setPurchaseditems(String purchaseditems) {
        this.purchaseditems = purchaseditems;
    }

    public String getDeliverystatus() {
        return deliverystatus;
    }

    public void setDeliverystatus(String deliverystatus) {
        this.deliverystatus = deliverystatus;
    }

    public List<Purchasedrecord> getPurchase() {
        return purchase;
    }

    public void setPurchase(List<Purchasedrecord> purchase) {
        this.purchase = purchase;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (recordid != null ? recordid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Purchasedrecord)) {
            return false;
        }
        Purchasedrecord other = (Purchasedrecord) object;
        if ((this.recordid == null && other.recordid != null) || (this.recordid != null && !this.recordid.equals(other.recordid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Purchasedrecord[ recordid=" + recordid + " ]";
    }
    
}
