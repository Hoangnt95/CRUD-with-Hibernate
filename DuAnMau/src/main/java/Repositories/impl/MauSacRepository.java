/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories.impl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import DomainModels.MauSac;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import Repositories.IRepository;
import Utilities.HibernateUtil;

/**
 *
 * @author Administrator
 */
public class MauSacRepository implements IRepository<MauSac> {

    @Override
    public List<MauSac> getAll() {
        List<MauSac> lists = new ArrayList<MauSac>();
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            String hql = "SELECT m FROM MauSac m";
            TypedQuery<MauSac> query = session.createQuery(hql, MauSac.class);
            lists = query.getResultList();
        }
        return lists;
    }

    @Override
    public MauSac getOne(String ten) {
        MauSac mauSac = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            String hql = "SELECT s FROM MauSac s WHERE s.ten = :ten";
            TypedQuery<MauSac> query = session.createQuery(hql, MauSac.class);
            query.setParameter("ten", ten);
            mauSac = query.getSingleResult();
        }
        return mauSac;
    }

    @Override
    public boolean add(MauSac m) {
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
    public boolean delete(MauSac t) {
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
