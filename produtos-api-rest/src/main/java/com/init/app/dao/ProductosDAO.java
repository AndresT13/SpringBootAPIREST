package com.init.app.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.init.app.entity.Productos;

public interface ProductosDAO extends JpaRepository<Productos, Long> {

	
}
