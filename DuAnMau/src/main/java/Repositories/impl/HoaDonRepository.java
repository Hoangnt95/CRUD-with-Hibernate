/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories.impl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import DomainModels.HoaDon;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import Repositories.IRepository;
import Utilities.HibernateUtil;
import ViewModels.HoaDonResponse;
import org.hibernate.query.NativeQuery;

/**
 *
 * @author Administrator
 */
public class HoaDonRepository implements IRepository<HoaDon> {

    @Override
    public List<HoaDon> getAll() {
        List<HoaDon> lists = new ArrayList<HoaDon>();
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            String hql = "SELECT m FROM HoaDon m";
            TypedQuery<HoaDon> query = session.createQuery(hql, HoaDon.class);
            lists = query.getResultList();
        }
        return lists;
    }

    @Override
    public HoaDon getOne(String ma) {
        HoaDon hoaDon = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            String hql = "SELECT s FROM HoaDon s WHERE s.ma = :ma";
            TypedQuery<HoaDon> query = session.createQuery(hql, HoaDon.class);
            query.setParameter("ma", ma);
            hoaDon = query.getSingleResult();
        }
        return hoaDon;
    }

    @Override
    public boolean add(HoaDon t) {
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
    public boolean delete(HoaDon t) {
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

    public List<HoaDonResponse> getListHD() {
        List<HoaDonResponse> lists = new ArrayList<HoaDonResponse>();
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            String hql = "SELECT new ViewModels.HoaDonResponse(m.ma, m.ngayTao,"
                    + "  m.nhanVien.ten, m.tinhTrang) FROM HoaDon m";
            Query query = session.createQuery(hql);
            lists = query.list();
        }
        return lists;
    }

    public int genMaHD() {
        String maHD = "";
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            NativeQuery query = session.createNativeQuery("SELECT MAX(CONVERT(INT, SUBSTRING(Ma, 3, 10) )) FROM HoaDon");
            if (query.getSingleResult() != null) {
                maHD = query.getSingleResult().toString();
            } else {
                maHD = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (maHD == null) {
            maHD = "0";
            int ma = Integer.valueOf(maHD);
            return ++ma;
        }
        int ma = Integer.valueOf(maHD);
        return ++ma;
    }

}
