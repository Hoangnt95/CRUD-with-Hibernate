/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories.impl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import DomainModels.NhaSanXuat;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import Repositories.IRepository;
import Utilities.HibernateUtil;

/**
 *
 * @author Administrator
 */
public class NsxReporitory implements IRepository<NhaSanXuat> {

    @Override
    public List<NhaSanXuat> getAll() {
        List<NhaSanXuat> lists = new ArrayList<NhaSanXuat>();
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            String hql = "SELECT m FROM NhaSanXuat m";
            TypedQuery<NhaSanXuat> query = session.createQuery(hql, NhaSanXuat.class);
            lists = query.getResultList();
        }
        return lists;
    }

    @Override
    public NhaSanXuat getOne(String ten) {
        NhaSanXuat nhaSanXuat = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            String hql = "SELECT s FROM NhaSanXuat s WHERE s.ten = :ten";
            TypedQuery<NhaSanXuat> query = session.createQuery(hql, NhaSanXuat.class);
            query.setParameter("ten", ten);
            nhaSanXuat = query.getSingleResult();
        }
        return nhaSanXuat;
    }

    @Override
    public boolean add(NhaSanXuat m) {
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
    public boolean delete(NhaSanXuat t) {
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
