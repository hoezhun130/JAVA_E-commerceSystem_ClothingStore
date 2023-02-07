/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author HoeZhun
 */
public class productService {
    @PersistenceContext
    EntityManager mgr;
    @Resource
    Query query;

    public productService(EntityManager mgr) {
        this.mgr = mgr;
    }

    public boolean addProduct(Productdetail product) {
        mgr.persist(product);
        return true;
    }

    public Productdetail findProductByCode(String code) {
        Productdetail product = mgr.find(Productdetail.class, code);
        return product;
    }

    public boolean deleteProduct(String code) {
        Productdetail product = findProductByCode(code);
        if (product != null) {
            mgr.remove(product);
            return true;
        }
        return false;
    }

    public List<Productdetail> findAll() {
        List productList = mgr.createNamedQuery("Productdetail.findAll").getResultList();
        return productList;
    }

    public boolean updateProduct(Productdetail product) {
        Productdetail tempPro = findProductByCode(product.getCode());
        if (tempPro != null) {
            tempPro.setPName(product.getPName());
            tempPro.setPrice(product.getPrice());
            tempPro.setRating(product.getRating());
            tempPro.setColor(product.getColor());
            tempPro.setSleevelength(product.getSleevelength());
            tempPro.setMaterial(product.getMaterial());
            tempPro.setSeason(product.getSeason());
            tempPro.setNeckline(product.getNeckline());
            tempPro.setStock(product.getStock());
            tempPro.setShipsfrom(product.getShipsfrom());
            tempPro.setDes(product.getDes());
            tempPro.setImg(product.getImg());
            return true;
        }
        return false;
    }
}
