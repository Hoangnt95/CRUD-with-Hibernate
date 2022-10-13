/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services.impl;

import java.util.List;
import DomainModels.SanPham;
import Repositories.impl.SanPhamRepository;
import Services.IService;

/**
 *
 * @author Administrator
 */
public class SanPhamService implements IService<SanPham> {

    private SanPhamRepository repo;

    public SanPhamService() {
        this.repo = new SanPhamRepository();
    }

    @Override
    public List<SanPham> getLists() {
        return repo.getAll();
    }

    @Override
    public SanPham getOne(String id) {
        return repo.getOne(id);
    }

    @Override
    public String addOrUpdate(SanPham m) {
        if (!repo.add(m)) {
            return "That bai";
        }
        return "Thanh cong";
    }

    @Override
    public String delete(SanPham sanPham) {
        if (sanPham.getId().equals("")) {
            return "Id khong dc de trong";
        }
        if (!repo.delete(sanPham)) {
            return "That bai";
        }
        return "Thanh cong";
    }
}
