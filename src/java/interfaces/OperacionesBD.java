/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.ArrayList;
/**
 *
 * @author Daniel
 */
public interface OperacionesBD <T>{
    public abstract Boolean insert(T t);
    public abstract Boolean update(T t);
    public abstract Boolean delete(Object id);
    public abstract ArrayList<T> selectAll();
    public abstract T selectById(Object idd);
    public abstract ArrayList<T> selectByName(String name);
    public abstract void closeConnection();
}
