/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories.impl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import DomainModels.GioHangChiTiet;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import Repositories.IRepository;
import Utilities.HibernateUtil;

/**
 *
 * @author Administrator
 */
public class GioHangCTRepository implements IRepository<GioHangChiTiet> {

    @Override
    public List<GioHangChiTiet> getAll() {
        List<GioHangChiTiet> lists = new ArrayList<GioHangChiTiet>();
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            String hql = "SELECT m FROM GioHangChiTiet m";
            TypedQuery<GioHangChiTiet> query = session.createQuery(hql, GioHangChiTiet.class);
            lists = query.getResultList();
        }
        return lists;
    }

    @Override
    public GioHangChiTiet getOne(String id) {
        GioHangChiTiet gioHangChiTiet = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            gioHangChiTiet = session.get(GioHangChiTiet.class, id);
        }
        return gioHangChiTiet;
    }

    @Override
    public boolean add(GioHangChiTiet t) {
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
    public boolean delete(GioHangChiTiet t) {
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
