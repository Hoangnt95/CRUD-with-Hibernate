/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories.impl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import DomainModels.CuaHang;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import Repositories.IRepository;
import Utilities.HibernateUtil;

/**
 *
 * @author Administrator
 */
public class CuaHangRepository implements IRepository<CuaHang> {

    @Override
    public List<CuaHang> getAll() {
        List<CuaHang> lists = new ArrayList<CuaHang>();
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            String hql = "SELECT m FROM CuaHang m";
            TypedQuery<CuaHang> query = session.createQuery(hql, CuaHang.class);
            lists = query.getResultList();
        }
        return lists;
    }

    @Override
    public CuaHang getOne(String ten) {
        CuaHang cuaHang = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            String hql = "SELECT s FROM CuaHang s WHERE s.ten = :ten";
            TypedQuery<CuaHang> query = session.createQuery(hql, CuaHang.class);
            query.setParameter("ten", ten);
            cuaHang = query.getSingleResult();
        }
        return cuaHang;
    }

    @Override
    public boolean add(CuaHang m) {
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
    public boolean delete(CuaHang m) {
        boolean check = false;
        Transaction tran = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            tran = session.beginTransaction();
            session.delete(m);
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
