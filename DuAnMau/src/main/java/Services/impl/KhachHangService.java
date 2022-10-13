/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services.impl;

import java.util.List;
import DomainModels.KhachHang;
import Repositories.impl.KhachHangRepository;
import Services.IService;

/**
 *
 * @author Administrator
 */
public class KhachHangService implements IService<KhachHang> {

    private KhachHangRepository repo;

    public KhachHangService() {
        this.repo = new KhachHangRepository();
    }

    @Override
    public List<KhachHang> getLists() {
        return repo.getAll();
    }

    @Override
    public KhachHang getOne(String id) {
        return repo.getOne(id);
    }

    @Override
    public String addOrUpdate(KhachHang t) {
        if (!repo.add(t)) {
            return "That bai";
        }
        return "Thanh cong";
    }

    @Override
    public String delete(KhachHang khachHang) {
        if (khachHang.getId().equals("")) {
            return "Id khong dc de trong";
        }
        if (!repo.delete(khachHang)) {
            return "That bai";
        }
        return "Thanh cong";
    }

}
