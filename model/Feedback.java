/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
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
@Table(name = "FEEDBACK")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Feedback.findAll", query = "SELECT f FROM Feedback f"),
    @NamedQuery(name = "Feedback.findByFbId", query = "SELECT f FROM Feedback f WHERE f.fbId = :fbId"),
    @NamedQuery(name = "Feedback.findByRate", query = "SELECT f FROM Feedback f WHERE f.rate = :rate"),
    @NamedQuery(name = "Feedback.findByName", query = "SELECT f FROM Feedback f WHERE f.name = :name"),
    @NamedQuery(name = "Feedback.findByComment", query = "SELECT f FROM Feedback f WHERE f.comment = :comment"),
    @NamedQuery(name = "Feedback.findByReply", query = "SELECT f FROM Feedback f WHERE f.reply = :reply"),
    @NamedQuery(name = "Feedback.findById", query = "SELECT f FROM Feedback f WHERE f.id = :id")})
public class Feedback implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "FB_ID")
    private Integer fbId;
    @Column(name = "RATE")
    private Integer rate;
    @Size(max = 255)
    @Column(name = "NAME")
    private String name;
    @Size(max = 255)
    @Column(name = "COMMENT")
    private String comment;
    @Size(max = 255)
    @Column(name = "REPLY")
    private String reply;
    @Column(name = "ID")
    private Integer id;

    public Feedback() {
    }

    public Feedback(Integer fbId) {
        this.fbId = fbId;
    }

    public Feedback(Integer rate, String name, String comment, int id) {
        this.rate = rate;
        this.name = name;
        this.comment = comment;
        this.id = id;
    }

    public Integer getFbId() {
        return fbId;
    }

    public void setFbId(Integer fbId) {
        this.fbId = fbId;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fbId != null ? fbId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Feedback)) {
            return false;
        }
        Feedback other = (Feedback) object;
        if ((this.fbId == null && other.fbId != null) || (this.fbId != null && !this.fbId.equals(other.fbId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Feedback[ fbId=" + fbId + " ]";
    }

}
