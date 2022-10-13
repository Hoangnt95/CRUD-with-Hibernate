/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services.impl;

import java.util.List;
import DomainModels.ChiTietSP;
import Repositories.impl.ChiTietSPRepository;
import Services.IService;
import ViewModels.SanPhamResponse;

/**
 *
 * @author Administrator
 */
public class ChiTietSPService implements IService<ChiTietSP> {

    private ChiTietSPRepository repo;

    public ChiTietSPService() {
        this.repo = new ChiTietSPRepository();
    }

    @Override
    public List<ChiTietSP> getLists() {
        return this.repo.getAll();
    }

    @Override
    public ChiTietSP getOne(String id) {
        return this.repo.getOne(id);
    }

    @Override
    public String addOrUpdate(ChiTietSP t) {
        if (!repo.add(t)) {
            return "That bai";
        }
        return "Thanh cong";
    }

    @Override
    public String delete(ChiTietSP chiTietSP) {
        if (chiTietSP.getId().equals("")) {
            return "Id khong dc de trong";
        }
        if (!repo.delete(chiTietSP)) {
            return "That bai";
        }
        return "Thanh cong";
    }

    public List<SanPhamResponse> getListSPBH() {
        return repo.getListSPBH();
    }

}
