/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;
import javax.annotation.Resource;
import javax.persistence.*;

public class StaffService {

    @PersistenceContext
    EntityManager mgr;
    @Resource
    Query query;

    public StaffService(EntityManager mgr) {
        this.mgr = mgr;
    }

    public boolean addStaff(Staff staff) {
        mgr.persist(staff);
        return true;
    }

    public Staff findStaffID(String id) {
        Staff staff = mgr.find(Staff.class, id);
        return staff;
    }

    public boolean deleteStaff(String id) {
        Staff staff = findStaffID(id);
        if (staff != null) {
            mgr.remove(staff);
            return true;
        }
        return false;
    }

    public List<Staff> findAll() {
        List staffList = mgr.createNamedQuery("Staff.findAll").getResultList();
        return staffList;
    }
    
    public List<Purchasedrecord> findAll_PurchaseRecord() {
        List purRecord = mgr.createNamedQuery("Purchasedrecord.findAll").getResultList();
        return purRecord;
    }

    public boolean updateStaff(Staff staff) {
        Staff staffID = findStaffID(staff.getId());
        if (staffID != null) {
            staffID.setName(staff.getName());
            staffID.setPhone(staff.getPhone());
            staffID.setAge(staff.getAge());
            staffID.setEmail(staff.getEmail());
            staffID.setPassword(staff.getPassword());

            return true;
        }
        return false;
    }

}
