package com.init.app.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.init.app.dao.ProductosDAO;
import com.init.app.entity.Productos;

@RestController
@RequestMapping("products")
public class ProductosREST {
	
	@Autowired
	private ProductosDAO productDAO;
	
//	@RequestMapping("products")
	@GetMapping
	public ResponseEntity<List<Productos>> getProductos() {
	 List<Productos> products =	productDAO.findAll();
		
		return ResponseEntity.ok(products);
	}
	
	@RequestMapping(value="{productId}")
	public ResponseEntity<Productos> getProductosById(@PathVariable("productId") Long productId) {
	Optional<Productos> optionalProduct = productDAO.findById(productId);
		if (optionalProduct.isPresent()) {
			return ResponseEntity.ok(optionalProduct.get());
		}
		return ResponseEntity.noContent().build();		
		
	}
	
	@PostMapping
	public ResponseEntity<Productos> createProductos(@RequestBody Productos product) {
		Productos newProduct = productDAO.save(product);
		return ResponseEntity.ok(newProduct);		
	}
	
	@DeleteMapping(value="{productId}")
	public ResponseEntity<Void> deleteProductos(@PathVariable("productId") Long productId) {
		productDAO.deleteById(productId);
		return ResponseEntity.ok(null);		
	}
	
	
	@RequestMapping(value="hola", method=RequestMethod.GET)
	public String hello() {
		return "hello world";
	}
	
	@PutMapping
	public ResponseEntity<Productos> updateProducto(@RequestBody Productos product) {
		Optional<Productos> optionalProduct = productDAO.findById(product.getId());
		if (optionalProduct.isPresent()) {
			Productos updateProdutc = optionalProduct.get();
			updateProdutc.setName(product.getName());
			productDAO.save(updateProdutc);
			return ResponseEntity.ok(updateProdutc);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
