package com.Admin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Admin.Dao.ProductDao;
import com.Admin.entity.Product;
@Service
public class ProductServiceimpl implements ProductService{
	
	@Autowired
	ProductDao dao;

	@Override
	public Product addpro(Product p) {
		// TODO Auto-generated method stub
		return dao.save(p);
	}

	@Override
	public Product update(Product p) {
		// TODO Auto-generated method stub
		return dao.save(p);
	}

	@Override
	public boolean delete(int id) {
		if(!dao.existsById(id)) {
			return false;
		}
		return true;
	}

	@Override
	public List<Product> fetchall() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public Product findbyid(int id) {
		Product p = dao.findById(id).get();
		return p;
	}

}
