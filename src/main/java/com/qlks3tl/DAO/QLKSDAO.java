/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlks3tl.DAO;

import java.util.List;

/**
 *
 * @author Acer
 */
abstract public class QLKSDAO <EntityType, KeyType> {
     abstract public void insert(EntityType entity);

    abstract public void update(EntityType entity);

    abstract public void delete(KeyType id);

    abstract public EntityType selectebyID(KeyType id);

    abstract public List<EntityType> selectAll();

    abstract public List<EntityType> selectbySql(String sql, Object... args);
}
