/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import java.util.List;
import DomainModels.MauSac;

/**
 *
 * @author Administrator
 */
public interface IService<T> {

    List<T> getLists();

    T getOne(String id);

    String addOrUpdate(T t);

    String delete(T t);
}
