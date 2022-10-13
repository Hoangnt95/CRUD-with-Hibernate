/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services.impl;

import java.util.List;
import DomainModels.MauSac;
import Repositories.IRepository;
import Repositories.impl.MauSacRepository;
import Services.IService;

/**
 *
 * @author Administrator
 */
public class MauSacService implements IService<MauSac> {

    private MauSacRepository repo;

    public MauSacService() {
        this.repo = new MauSacRepository();
    }

    @Override
    public List<MauSac> getLists() {
        return repo.getAll();
    }

    @Override
    public MauSac getOne(String id) {
        return repo.getOne(id);
    }

    @Override
    public String addOrUpdate(MauSac m) {
        if (!repo.add(m)) {
            return "That bai";
        }
        return "Thanh cong";
    }

    @Override
    public String delete(MauSac mauSac) {
        if (mauSac.getId().equals("")) {
            return "Id khong dc de trong";
        }
        if (!repo.delete(mauSac)) {
            return "That bai";
        }
        return "Thanh cong";
    }

}
