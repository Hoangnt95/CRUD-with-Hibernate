/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories.impl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import DomainModels.ChiTietSP;
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
public class ChiTietSPRepository implements IRepository<ChiTietSP> {

    @Override
    public List<ChiTietSP> getAll() {
        List<ChiTietSP> lists = new ArrayList<ChiTietSP>();
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            String hql = "SELECT m FROM ChiTietSP m";
            TypedQuery<ChiTietSP> query = session.createQuery(hql, ChiTietSP.class);
            lists = query.getResultList();
        }
        return lists;
    }

    @Override
    public ChiTietSP getOne(String ten) {
        ChiTietSP chiTietSP = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            String hql = "SELECT new models.ChiTietSP(s.id ) "
                    + "FROM ChiTietSP s WHERE s.sanPham.ten = :ten";
            TypedQuery<ChiTietSP> query = session.createQuery(hql, ChiTietSP.class);
            query.setParameter("ten", ten);
            chiTietSP = query.getSingleResult();
        }
        return chiTietSP;
    }

    @Override
    public boolean add(ChiTietSP t) {
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
    public boolean delete(ChiTietSP t) {
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

    public List<SanPhamResponse> getListSPBH() {
        List<SanPhamResponse> listsSp = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            String hql = "SELECT new ViewModels.SanPhamResponse(sp.ma, sp.ten,"
                    + "ctsp.namBH, ctsp.moTa, ctsp.soLuongTon, ctsp.giaBan, ctsp.giaNhap)"
                    + " FROM ChiTietSP ctsp left join ctsp.sanPham sp";
            Query query = session.createQuery(hql);
            listsSp = query.list();
        }
        return listsSp;
    }

}
