package com.te.productmanagementapp.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="product_info")
public class ProductInfoBean implements Serializable{
	@Id
	@Column
	private int pid;
	@Column
	private String pname;
	@Column
	private Date mgDate;
	@Column
	private Date exDate;
	@Column
	private int price;
	@Column
	private int quantity;
	@Column
	private int password;
	
}
