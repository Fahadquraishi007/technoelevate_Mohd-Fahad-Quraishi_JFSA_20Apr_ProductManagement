package com.te.productmanagementapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.te.productmanagementapp.beans.ProductInfoBean;


@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDAO dao;

	@Override
	public ProductInfoBean authenticate(int pid, String pwd) {
		if (pid <= 0) {
			return null;
		} else {
			return dao.authenticate(pid, pwd);
		}

	}

	@Override
	public ProductInfoBean getProductData(int pid) {
		if (id <= 0) {
			return null;
		}
		return dao.getProductData(pid);
	}

	@Override
	public boolean deleteProductData(int pid) {

		return dao.deleteProductData(pid);
	}

	@Override
	public boolean addProduct(ProductInfoBean productInfoBean) {

		return dao.addProduct(productInfoBean);
	}

	@Override
	public boolean updateRecord(ProductInfoBean productInfoBean) {
		// TODO Auto-generated method stub
		return dao.updateRecord(productInfoBean);
	}

	@Override
	public List<ProductInfoBean> getAllProducts() {
		// TODO Auto-generated method stub
		return dao.getAllEmployees();
	}

}