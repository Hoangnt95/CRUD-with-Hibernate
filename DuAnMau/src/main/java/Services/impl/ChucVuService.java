/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services.impl;

import java.util.List;
import javax.swing.JOptionPane;
import DomainModels.ChucVu;
import Repositories.impl.ChucVuRepository;
import Services.IService;

/**
 *
 * @author Administrator
 */
public class ChucVuService implements IService<ChucVu> {

    private ChucVuRepository repo;

    public ChucVuService() {
        this.repo = new ChucVuRepository();
    }

    @Override
    public List<ChucVu> getLists() {
        return repo.getAll();
    }

    @Override
    public ChucVu getOne(String id) {
        return repo.getOne(id);
    }

    @Override
    public String addOrUpdate(ChucVu t) {
        if (!repo.add(t)) {
            return "That bai";
        }
        return "Thanh cong";
    }

    @Override
    public String delete(ChucVu chucVu) {
        if (chucVu.getId().equals("")) {
            return "Id khong dc de trong";
        }
        if (!repo.delete(chucVu)) {
            return "That bai";
        }
        return "Thanh cong";
    }

}
