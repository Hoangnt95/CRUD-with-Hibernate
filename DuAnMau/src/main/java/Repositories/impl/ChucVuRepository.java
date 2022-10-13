/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories.impl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import DomainModels.ChucVu;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import Repositories.IRepository;
import Utilities.HibernateUtil;

/**
 *
 * @author Administrator
 */
public class ChucVuRepository implements IRepository<ChucVu> {

    @Override
    public List<ChucVu> getAll() {
        List<ChucVu> lists = new ArrayList<ChucVu>();
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            String hql = "SELECT m FROM ChucVu m";
            TypedQuery<ChucVu> query = session.createQuery(hql, ChucVu.class);
            lists = query.getResultList();
        }
        return lists;
    }

    @Override
    public ChucVu getOne(String ten) {
        ChucVu chucVu = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            String hql = "SELECT s FROM ChucVu s WHERE s.ten = :ten";
            TypedQuery<ChucVu> query = session.createQuery(hql, ChucVu.class);
            query.setParameter("ten", ten);
            chucVu = query.getSingleResult();
        }
        return chucVu;
    }

    @Override
    public boolean add(ChucVu m) {
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
    public boolean delete(ChucVu m) {
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
