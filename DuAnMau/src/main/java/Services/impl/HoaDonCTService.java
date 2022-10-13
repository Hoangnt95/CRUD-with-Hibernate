/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services.impl;

import java.util.List;
import DomainModels.HoaDonChiTiet;
import Repositories.impl.HoaDonCTRepository;
import Services.IService;

/**
 *
 * @author Administrator
 */
public class HoaDonCTService implements IService<HoaDonChiTiet> {

    private HoaDonCTRepository repo;

    public HoaDonCTService() {
        this.repo = new HoaDonCTRepository();
    }

    @Override
    public List<HoaDonChiTiet> getLists() {
        return this.repo.getAll();
    }

    @Override
    public HoaDonChiTiet getOne(String id) {
        return this.repo.getOne(id);
    }

    @Override
    public String addOrUpdate(HoaDonChiTiet t) {
        if (!repo.add(t)) {
            return "That bai";
        }
        return "Thanh cong";
    }

    @Override
    public String delete(HoaDonChiTiet hoaDonChiTiet) {
        if (!repo.delete(hoaDonChiTiet)) {
            return "That bai";
        }
        return "Thanh cong";
    }

}
