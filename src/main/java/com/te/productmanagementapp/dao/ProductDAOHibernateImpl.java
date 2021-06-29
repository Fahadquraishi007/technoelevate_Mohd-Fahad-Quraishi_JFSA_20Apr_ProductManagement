package com.te.productmanagementapp.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.te.productmanagementapp.beans.ProductInfoBean;
import com.te.productmanagementapp.customexp.ProductException;

@Repository
public class ProductDAOHibernateImpl implements ProductDAO {

	@Override
	public ProductInfoBean authenticate(int id, String pwd) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("emsPeristenceUnit");
		EntityManager manager = factory.createEntityManager();
		ProductInfoBean infoBean = manager.find(ProductInfoBean.class, id);

		if (infoBean != null) {
			if (infoBean.getPassword().equals(pwd)) {
				return infoBean;
			} else {
				throw new ProductException("Password is wrong");
			}
		} else {
			throw new ProductException("Invalid ID");
		}

	}

	@Override
	public ProductInfoBean getProductData(int id) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("emsPeristenceUnit");
		EntityManager manager = factory.createEntityManager();
		ProductInfoBean infoBean = manager.find(ProductInfoBean.class, id);
		manager.close();
		factory.close();
		return infoBean;
	}

	@Override
	public boolean deleteProductData(int id) {
		boolean isDeleted = false;
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("emsPeristenceUnit");
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		try {
			transaction.begin();
			ProductInfoBean infoBean = manager.find(ProductInfoBean.class, id);
			manager.remove(infoBean);
			transaction.commit();
			isDeleted = true;
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}

		return isDeleted;
	}

	@Override
	public boolean addEmployee(ProductInfoBean productInfoBean) {
		boolean isInserted = false;
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("emsPeristenceUnit");
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();

		try {
			transaction.begin();
			manager.persist(productInfoBean);
			transaction.commit();
			isInserted = true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}

		return isInserted;
	}

	@Override
	public boolean updateRecord(ProductInfoBean productInfoBean) {
		boolean isUpdated = false;
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("emsPeristenceUnit");
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		try {
			transaction.begin();
			ProductInfoBean actualInfo = manager.find(ProductInfoBean.class, productInfoBean.getPid());

			if (productInfoBean.getPname() != null && productInfoBean.getPname() != "") {
				actualInfo.setPname(productInfoBean.getPname());
			}

			if (productInfoBean.getMgDate() != null) {
				actualInfo.setMgDate(productInfoBean.getMgDate());
			}

			if (productInfoBean.getPassword() != null && productInfoBean.getPassword() != "") {
				actualInfo.setPassword(productInfoBean.getPassword());
			}

			transaction.commit();
			isUpdated = true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}

		return isUpdated;
	}

	@Override
	public List<ProductInfoBean> getAllProducts() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("emsPeristenceUnit");
		EntityManager manager = factory.createEntityManager();
		Query query = manager.createQuery("from EmployeeInfoBean");
		ArrayList<ProductInfoBean> productInfoBeans = new ArrayList<ProductInfoBean>();
		try {
			productInfoBeans = (ArrayList<ProductInfoBean>) query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			productInfoBeans = null;
		}

		return productInfoBeans;
	}

}