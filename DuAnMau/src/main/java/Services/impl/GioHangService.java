/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services.impl;

import java.util.List;
import DomainModels.GioHang;
import Repositories.impl.GioHangRepository;
import Services.IService;

/**
 *
 * @author Administrator
 */
public class GioHangService implements IService<GioHang> {

    private GioHangRepository repo;

    public GioHangService() {
        this.repo = new GioHangRepository();
    }

    @Override
    public List<GioHang> getLists() {
        return this.repo.getAll();
    }

    @Override
    public GioHang getOne(String id) {
        return this.repo.getOne(id);
    }

    @Override
    public String addOrUpdate(GioHang t) {
        if (!repo.add(t)) {
            return "That bai";
        }
        return "Thanh cong";
    }

    @Override
    public String delete(GioHang gioHang) {
        if (gioHang.getId().equals("")) {
            return "Id khong dc de trong";
        }
        if (!repo.delete(gioHang)) {
            return "That bai";
        }
        return "Thanh cong";
    }

}
