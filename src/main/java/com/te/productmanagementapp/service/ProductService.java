package com.te.productmanagementapp.service;

import java.util.List;

import com.te.productmanagementapp.beans.ProductInfoBean;

public interface ProductService {

	public ProductInfoBean authenticate(int pid, String pwd);

	public ProductInfoBean getProductData(int pid);

	public boolean deleteProductData(int id);
	
	public boolean addProduct(ProductInfoBean ProductInfoBean);
	
	public boolean updateRecord(ProductInfoBean productInfoBean);
	
	public List<ProductInfoBean> getAllProducts();
}