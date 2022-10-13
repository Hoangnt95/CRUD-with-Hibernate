/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services.impl;

import java.util.List;
import DomainModels.HoaDon;
import Repositories.impl.HoaDonRepository;
import Services.IService;
import ViewModels.HoaDonResponse;

/**
 *
 * @author Administrator
 */
public class HoaDonService implements IService<HoaDon> {

    private HoaDonRepository repo;

    public HoaDonService() {
        this.repo = new HoaDonRepository();
    }

    @Override
    public List<HoaDon> getLists() {
        return repo.getAll();
    }

    @Override
    public HoaDon getOne(String id) {
        return repo.getOne(id);
    }

    @Override
    public String addOrUpdate(HoaDon t) {
        if (!repo.add(t)) {
            return "That bai";
        }
        return "Thanh cong";
    }

    @Override
    public String delete(HoaDon hoaDon) {
        if (hoaDon.getId().equals("")) {
            return "Id khong dc de trong";
        }
        if (!repo.delete(hoaDon)) {
            return "That bai";
        }
        return "Thanh cong";
    }

    public String genMaHD() {
        return "";
    }

    public List<HoaDonResponse> getAllHD() {
        return repo.getListHD();
    }

    public int genMa() {
        return repo.genMaHD();
    }

}
