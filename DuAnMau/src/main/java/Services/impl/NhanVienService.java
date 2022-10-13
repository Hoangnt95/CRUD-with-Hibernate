/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services.impl;

import java.util.List;
import DomainModels.NhanVien;
import Repositories.impl.NhanVienRepository;
import Services.IService;

/**
 *
 * @author Administrator
 */
public class NhanVienService implements IService<NhanVien> {

    private NhanVienRepository repo;

    public NhanVienService() {
        this.repo = new NhanVienRepository();
    }

    @Override
    public List<NhanVien> getLists() {
        return repo.getAll();
    }

    @Override
    public NhanVien getOne(String id) {
        return repo.getOne(id);
    }

    @Override
    public String addOrUpdate(NhanVien t) {
        if (!repo.add(t)) {
            return "That bai";
        }
        return "Thanh cong";
    }

    @Override
    public String delete(NhanVien nhanVien) {
        if (nhanVien.getId().equals("")) {
            return "Id khong dc de trong";
        }
        if (!repo.delete(nhanVien)) {
            return "That bai";
        }
        return "Thanh cong";
    }

}
