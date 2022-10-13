/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories.impl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import DomainModels.MauSac;
import DomainModels.SanPham;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import Repositories.IRepository;
import Utilities.HibernateUtil;
import ViewModels.SanPhamResponse;

/**
 *
 * @author Administrator
 */
public class SanPhamRepository implements IRepository<SanPham> {

    @Override
    public List<SanPham> getAll() {
        List<SanPham> lists = new ArrayList<SanPham>();
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            String hql = "SELECT m FROM SanPham m";
            TypedQuery<SanPham> query = session.createQuery(hql, SanPham.class);
            lists = query.getResultList();
        }
        return lists;
    }

    @Override
    public SanPham getOne(String ten) {
        SanPham sanPham = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            String hql = "SELECT s FROM SanPham s WHERE s.ten = :ten";
            TypedQuery<SanPham> query = session.createQuery(hql, SanPham.class);
            query.setParameter("ten", ten);
            sanPham = query.getSingleResult();
        }
        return sanPham;
    }

    @Override
    public boolean add(SanPham m) {
        boolean check = false;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            Transaction tran = session.beginTransaction();
            try {
                session.saveOrUpdate(m);
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
    public boolean delete(SanPham t) {
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
