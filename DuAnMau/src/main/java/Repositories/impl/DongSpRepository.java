/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories.impl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import DomainModels.DongSP;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import Repositories.IRepository;
import Utilities.HibernateUtil;

/**
 *
 * @author Administrator
 */
public class DongSpRepository implements IRepository<DongSP> {

    @Override
    public List<DongSP> getAll() {
        List<DongSP> lists = new ArrayList<DongSP>();
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            String hql = "SELECT m FROM DongSP m";
            TypedQuery<DongSP> query = session.createQuery(hql, DongSP.class);
            lists = query.getResultList();
        }
        return lists;
    }

    @Override
    public DongSP getOne(String ten) {
        DongSP dongSP = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            String hql = "SELECT s FROM DongSP s WHERE s.ten = :ten";
            TypedQuery<DongSP> query = session.createQuery(hql,DongSP.class);
            query.setParameter("ten", ten);
            dongSP = query.getSingleResult();
        }
        return dongSP;
    }

    @Override
    public boolean add(DongSP m) {
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
    public boolean delete(DongSP t) {
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
