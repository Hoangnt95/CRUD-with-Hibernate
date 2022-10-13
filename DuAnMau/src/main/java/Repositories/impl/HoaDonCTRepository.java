/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories.impl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import DomainModels.HoaDonChiTiet;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import Repositories.IRepository;
import Utilities.HibernateUtil;

/**
 *
 * @author Administrator
 */
public class HoaDonCTRepository implements IRepository<HoaDonChiTiet> {

    @Override
    public List<HoaDonChiTiet> getAll() {
        List<HoaDonChiTiet> lists = new ArrayList<HoaDonChiTiet>();
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            String hql = "SELECT m FROM HoaDonChiTiet m";
            TypedQuery<HoaDonChiTiet> query = session.createQuery(hql, HoaDonChiTiet.class);
            lists = query.getResultList();
        }
        return lists;
    }

    @Override
    public HoaDonChiTiet getOne(String ten) {
        HoaDonChiTiet hoaDonChiTiet = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            String hql = "SELECT s FROM HoaDonChiTiet s WHERE s.ten = :ten";
            TypedQuery<HoaDonChiTiet> query = session.createQuery(hql, HoaDonChiTiet.class);
            query.setParameter("ten", ten);
            hoaDonChiTiet = query.getSingleResult();
        }
        return hoaDonChiTiet;
    }

    @Override
    public boolean add(HoaDonChiTiet t) {
        boolean check = false;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            Transaction tran = session.beginTransaction();
            try {
                session.saveOrUpdate(t);
                tran.commit();
                check = true;
            } catch (Exception e) {
                e.printStackTrace();
                tran.rollback();
            }
        }
        return check;
    }

    @Override
    public boolean delete(HoaDonChiTiet t) {
        boolean check = false;
        Transaction tran = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            tran = session.beginTransaction();
            session.delete(t);
            tran.commit();
            check = true;
        } catch (Exception e) {
            e.printStackTrace();
            check = false;
            tran.rollback();
        }
        return check;
    }

}
