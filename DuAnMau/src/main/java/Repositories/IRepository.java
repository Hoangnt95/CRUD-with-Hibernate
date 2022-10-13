/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import java.util.List;

/**
 *
 * @author Administrator
 */
public interface IRepository<T> {

    List<T> getAll();

    T getOne(String ten);

    boolean add(T t);

    boolean delete(T t);
}
