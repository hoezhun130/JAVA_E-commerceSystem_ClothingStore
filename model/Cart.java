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
@Table(name = "CART")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cart.findAll", query = "SELECT c FROM Cart c"),
    @NamedQuery(name = "Cart.findByCartid", query = "SELECT c FROM Cart c WHERE c.cartid = :cartid"),
    @NamedQuery(name = "Cart.findByProductcode", query = "SELECT c FROM Cart c WHERE c.productcode = :productcode"),
    @NamedQuery(name = "Cart.findByProductname", query = "SELECT c FROM Cart c WHERE c.productname = :productname"),
    @NamedQuery(name = "Cart.findByColor", query = "SELECT c FROM Cart c WHERE c.color = :color"),
    @NamedQuery(name = "Cart.findByQty", query = "SELECT c FROM Cart c WHERE c.qty = :qty"),
    @NamedQuery(name = "Cart.findByPrice", query = "SELECT c FROM Cart c WHERE c.price = :price"),
    @NamedQuery(name = "Cart.findByTotalprice", query = "SELECT c FROM Cart c WHERE c.totalprice = :totalprice"),
    @NamedQuery(name = "Cart.findByImg", query = "SELECT c FROM Cart c WHERE c.img = :img"),
    @NamedQuery(name = "Cart.findByCustomerid", query = "SELECT c FROM Cart c WHERE c.customerid = :customerid")})
public class Cart implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CARTID")
    private Integer cartid;
    @Size(max = 255)
    @Column(name = "PRODUCTCODE")
    private String productcode;
    @Size(max = 255)
    @Column(name = "PRODUCTNAME")
    private String productname;
    @Size(max = 255)
    @Column(name = "COLOR")
    private String color;
    @Column(name = "QTY")
    private Integer qty;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PRICE")
    private Double price;
    @Column(name = "TOTALPRICE")
    private Double totalprice;
    @Size(max = 255)
    @Column(name = "IMG")
    private String img;
    @Column(name = "CUSTOMERID")
    private Integer customerid;

    public Cart() {
    }

    public Cart(Integer cartid) {
        this.cartid = cartid;
    }
    public Cart(String productcode, String productname,String color, Integer qty,Double price, String img, Integer customerid){
        this.productcode = productcode;
        this.productname = productname;
        this.color = color;
        this.qty = qty;
        this.price = price;
        this.img = img;
        this.customerid = customerid;
        
        totalprice = price * qty;
        
    }

    public Integer getCartid() {
        return cartid;
    }

    public void setCartid(Integer cartid) {
        this.cartid = cartid;
    }

    public String getProductcode() {
        return productcode;
    }

    public void setProductcode(String productcode) {
        this.productcode = productcode;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Double totalprice) {
        this.totalprice = totalprice;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getCustomerid() {
        return customerid;
    }

    public void setCustomerid(Integer customerid) {
        this.customerid = customerid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cartid != null ? cartid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cart)) {
            return false;
        }
        Cart other = (Cart) object;
        if ((this.cartid == null && other.cartid != null) || (this.cartid != null && !this.cartid.equals(other.cartid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Cart[ cartid=" + cartid + " ]";
    }
    
}
