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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ILLEGEAR
 */
@Entity
@Table(name = "PRODUCTDETAIL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Productdetail.findAll", query = "SELECT p FROM Productdetail p"),
    @NamedQuery(name = "Productdetail.findByCode", query = "SELECT p FROM Productdetail p WHERE p.code = :code"),
    @NamedQuery(name = "Productdetail.findByPName", query = "SELECT p FROM Productdetail p WHERE p.pName = :pName"),
    @NamedQuery(name = "Productdetail.findByPrice", query = "SELECT p FROM Productdetail p WHERE p.price = :price"),
    @NamedQuery(name = "Productdetail.findByRating", query = "SELECT p FROM Productdetail p WHERE p.rating = :rating"),
    @NamedQuery(name = "Productdetail.findByColor", query = "SELECT p FROM Productdetail p WHERE p.color = :color"),
    @NamedQuery(name = "Productdetail.findBySleevelength", query = "SELECT p FROM Productdetail p WHERE p.sleevelength = :sleevelength"),
    @NamedQuery(name = "Productdetail.findByMaterial", query = "SELECT p FROM Productdetail p WHERE p.material = :material"),
    @NamedQuery(name = "Productdetail.findBySeason", query = "SELECT p FROM Productdetail p WHERE p.season = :season"),
    @NamedQuery(name = "Productdetail.findByNeckline", query = "SELECT p FROM Productdetail p WHERE p.neckline = :neckline"),
    @NamedQuery(name = "Productdetail.findByStock", query = "SELECT p FROM Productdetail p WHERE p.stock = :stock"),
    @NamedQuery(name = "Productdetail.findByShipsfrom", query = "SELECT p FROM Productdetail p WHERE p.shipsfrom = :shipsfrom"),
    @NamedQuery(name = "Productdetail.findByDes", query = "SELECT p FROM Productdetail p WHERE p.des = :des"),
    @NamedQuery(name = "Productdetail.findByImg", query = "SELECT p FROM Productdetail p WHERE p.img = :img")})
public class Productdetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "CODE")
    private String code;
    @Size(max = 255)
    @Column(name = "P_NAME")
    private String pName;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PRICE")
    private Double price;
    @Column(name = "RATING")
    private Double rating;
    @Size(max = 255)
    @Column(name = "COLOR")
    private String color;
    @Size(max = 255)
    @Column(name = "SLEEVELENGTH")
    private String sleevelength;
    @Size(max = 255)
    @Column(name = "MATERIAL")
    private String material;
    @Size(max = 255)
    @Column(name = "SEASON")
    private String season;
    @Size(max = 255)
    @Column(name = "NECKLINE")
    private String neckline;
    @Column(name = "STOCK")
    private Integer stock;
    @Size(max = 255)
    @Column(name = "SHIPSFROM")
    private String shipsfrom;
    @Size(max = 255)
    @Column(name = "DES")
    private String des;
    @Size(max = 255)
    @Column(name = "IMG")
    private String img;

    public Productdetail() {
    }
     public Productdetail(String code, String pName, Double price, Double rating, String color, String sleevelength, String material, String season, String neckline, Integer stock, String shipsfrom, String des, String img) {
        this.code = code;
        this.pName = pName;
        this.price = price;
        this.rating = rating;
        this.color = color;
        this.sleevelength = sleevelength;
        this.material = material;
        this.season = season;
        this.neckline = neckline;
        this.stock = stock;
        this.shipsfrom = shipsfrom;
        this.des = des;
        this.img = img;
    }

    public Productdetail(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPName() {
        return pName;
    }

    public void setPName(String pName) {
        this.pName = pName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSleevelength() {
        return sleevelength;
    }

    public void setSleevelength(String sleevelength) {
        this.sleevelength = sleevelength;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getNeckline() {
        return neckline;
    }

    public void setNeckline(String neckline) {
        this.neckline = neckline;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getShipsfrom() {
        return shipsfrom;
    }

    public void setShipsfrom(String shipsfrom) {
        this.shipsfrom = shipsfrom;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (code != null ? code.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Productdetail)) {
            return false;
        }
        Productdetail other = (Productdetail) object;
        if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Productdetail[ code=" + code + " ]";
    }
    
}
