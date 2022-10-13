/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services.impl;

import java.util.List;
import DomainModels.CuaHang;
import Repositories.impl.CuaHangRepository;
import Services.IService;

/**
 *
 * @author Administrator
 */
public class CuaHangService implements IService<CuaHang> {

    private CuaHangRepository repo;

    public CuaHangService() {
        this.repo = new CuaHangRepository();
    }

    @Override
    public List<CuaHang> getLists() {
        return repo.getAll();
    }

    @Override
    public CuaHang getOne(String id) {
        return repo.getOne(id);
    }

    @Override
    public String addOrUpdate(CuaHang t) {
        if (!repo.add(t)) {
            return "That bai";
        }
        return "Thanh cong";
    }

    @Override
    public String delete(CuaHang cuaHang) {
        if (cuaHang.getId().equals("")) {
            return "Id khong dc de trong";
        }
        if (!repo.delete(cuaHang)) {
            return "That bai";
        }
        return "Thanh cong";
    }

}
