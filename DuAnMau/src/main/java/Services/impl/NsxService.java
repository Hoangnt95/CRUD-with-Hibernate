/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services.impl;

import java.util.List;
import DomainModels.NhaSanXuat;
import Repositories.impl.NsxReporitory;
import Services.IService;

/**
 *
 * @author Administrator
 */
public class NsxService implements IService<NhaSanXuat> {

    private NsxReporitory repo;

    public NsxService() {
        this.repo = new NsxReporitory();
    }

    @Override
    public List<NhaSanXuat> getLists() {
        return repo.getAll();
    }

    @Override
    public NhaSanXuat getOne(String id) {
        return repo.getOne(id);
    }

    @Override
    public String addOrUpdate(NhaSanXuat m) {
        if (!repo.add(m)) {
            return "That bai";
        }
        return "Thanh cong";
    }

    @Override
    public String delete(NhaSanXuat nhaSanXuat) {
        if (nhaSanXuat.getId().equals("")) {
            return "Id khong dc de trong";
        }
        if (!repo.delete(nhaSanXuat)) {
            return "That bai";
        }
        return "Thanh cong";
    }
}
