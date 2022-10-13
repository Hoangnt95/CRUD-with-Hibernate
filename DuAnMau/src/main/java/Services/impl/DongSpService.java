/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services.impl;

import java.util.List;
import DomainModels.DongSP;
import Repositories.impl.DongSpRepository;
import Services.IService;

/**
 *
 * @author Administrator
 */
public class DongSpService implements IService<DongSP> {

    private DongSpRepository repo;

    public DongSpService() {
        this.repo = new DongSpRepository();
    }

    @Override
    public List<DongSP> getLists() {
        return repo.getAll();
    }

    @Override
    public DongSP getOne(String id) {
        return repo.getOne(id);
    }

    @Override
    public String addOrUpdate(DongSP m) {
        if (!repo.add(m)) {
            return "That bai";
        }
        return "Thanh cong";
    }

    @Override
    public String delete(DongSP dongSP) {
        if (dongSP.getId().equals("")) {
            return "Id khong dc de trong";
        }
        if (!repo.delete(dongSP)) {
            return "That bai";
        }
        return "Thanh cong";
    }

}
