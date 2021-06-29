package com.te.productmanagementapp.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.te.productmanagementapp.beans.ProductInfoBean;
import com.te.productmanagementapp.service.ProductService;

@Controller
public class ProductController {

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		System.out.println("init Binder");
		CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true);
		binder.registerCustomEditor(Date.class, editor);
	}

	@Autowired
	private ProductService service;

	@GetMapping("/login")
	public String getProductForm() {
		return "productLogin";
	}// getEmpForm

	@PostMapping("/login")
	public String authenticate(int id, String password, HttpServletRequest request, ModelMap map) {
		ProductInfoBean infoBean = service.authenticate(id, password);
		System.out.println(infoBean);
		if (infoBean != null) {
			HttpSession session = request.getSession();
			session.setAttribute("loggedIn", infoBean);
//			session.setMaxInactiveInterval(3600);
			map.addAttribute("name", infoBean.getPname());
			return "productHome";
		} else {
			map.addAttribute("errMsg", "Invalid Credentials");
			return "login";
		}
	}// authenticate

	@GetMapping("/searchPage")
	public String getSearchPage(HttpSession session, ModelMap map) {
		ProductInfoBean infoBean = (ProductInfoBean) session.getAttribute("loggedIn");
		System.out.println(infoBean);
		if (infoBean != null) {
			System.out.println("valid");
			return "productSearchPage";
		} else {
			map.addAttribute("errMsg", "Please Login First");
			return "login";
		}
	}// getSearchPage

	@GetMapping("/search")
	public String getProductData(int id,
			@SessionAttribute(name = "loggedIn", required = false) ProductInfoBean infoBean, ModelMap map) {
		if (infoBean != null) {
			ProductInfoBean productInfoBean = service.getProductData(id);
			if (productInfoBean != null) {
				map.addAttribute("empData", productInfoBean);
			} else {
				map.addAttribute("errMsg", "Data not Found");
			}
			return "productSearchPage";
		} else {
			map.addAttribute("errMsg", "Please Login First");
			return "login";
		}
	}

	@GetMapping("/logout")
	public String logout(HttpSession session, ModelMap map) {
		session.invalidate();
		map.addAttribute("msg", "logout successfull");
		return "login";
	}// logout

	@GetMapping("/getDeleteForm")
	public String getDeleteForm(@SessionAttribute(name = "loggedIn", required = false) ProductInfoBean infoBean,
			ModelMap map) {
		if (infoBean != null) {
			return "deleteProduct";
		} else {
			map.addAttribute("errMsg", "Please Login First");
			return "login";
		}
	}//

	@GetMapping("/delete")
	public String deleteData(int id, @SessionAttribute(name = "loggedIn", required = false) ProductInfoBean infoBean,
			ModelMap map) {
		if (infoBean != null) {
			if (service.deleteProductData(id)) {
				map.addAttribute("msg", "Data Deleted successfully for id : " + id);
			} else {
				map.addAttribute("msg", "Could not find Record for id : " + id);
			}
			return "deleteProduct";
		} else {
			map.addAttribute("errMsg", "Please Login First");
			return "login";
		}

	}//

	@GetMapping("/addProduct")
	public String getAddFrom(@SessionAttribute(name = "loggedIn", required = false) ProductInfoBean infoBean,
			ModelMap map) {
		if (infoBean != null) {
			return "insertProduct";
		} else {
			map.addAttribute("errMsg", "Please Login First");
			return "login";
		}

	}//

	@PostMapping("/add")
	public String addProduct(ProductInfoBean productInfoBean,
			@SessionAttribute(name = "loggedIn", required = false) ProductInfoBean infoBean, ModelMap map) {
		if (infoBean != null) {
			if (service.addProduct(productInfoBean)) {
				map.addAttribute("msg", "Successfully Inserted");
			} else {
				map.addAttribute("msg", "Failed to Insert");
			}
			return "insertProduct";
		} else {
			map.addAttribute("errMsg", "Please Login First");
			return "login";
		}

	}// add Employee

	@GetMapping("/updateProduct")
	public String getUpadatePage(@SessionAttribute(name = "loggedIn", required = false) ProductInfoBean infoBean,
			ModelMap map) {
		if (infoBean != null) {
			map.addAttribute("id", infoBean);
			return "updateProduct";
		} else {
			map.addAttribute("errMsg", "Please Login First");
			return "login";

		}
	}//

	@PostMapping("/update")
	public String updateProductData(@SessionAttribute(name = "loggedIn", required = false) ProductInfoBean infoBean,
			ModelMap map, ProductInfoBean productInfoBean) {
		if (infoBean != null) {
			if (service.updateRecord(productInfoBean)) {
				map.addAttribute("msg", "Updated Successfully");
				map.addAttribute("id", productInfoBean);
			} else {
				map.addAttribute("msg", "Updation Failed");
				map.addAttribute("id", infoBean);
			}
			return "updateProduct";
		} else {
			map.addAttribute("errMsg", "Please Login First");
			return "login";
		}
	}//

	@GetMapping("/getAll")
	public String getAllRecords(@SessionAttribute(name = "loggedIn", required = false) ProductInfoBean infoBean,
			ModelMap map) {
		if (infoBean != null) {
			List<ProductInfoBean> productInfoBeans = service.getAllProducts();
			if (productInfoBeans != null) {
				
				map.addAttribute("infos", productInfoBeans);
			}else {
				map.addAttribute("errMsg", "No Records Found");
			}
			map.addAttribute("name", infoBean.getPname());	
			return "productHome";
		} else {
			map.addAttribute("errMsg", "Please Login First");
			return "login";
		}
	}

}