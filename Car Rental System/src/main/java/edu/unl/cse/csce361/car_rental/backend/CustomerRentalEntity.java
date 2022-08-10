package edu.unl.cse.csce361.car_rental.backend;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import javax.persistence.OneToOne;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


@Entity
@Table(name = "CustomerRental")
@Access(value=AccessType.FIELD)
public class CustomerRentalEntity implements CustomerRental{
	
	public static final int VIN_MAXIMUM_SIZE = 17;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Id")
	private long Id;

	@Column(name = "Account", nullable = false, unique = true)
	private String account;
	
	@Column(name = "vin", length = VIN_MAXIMUM_SIZE)
	private String vin;

	@Column(name = "Customer_kind")
//	@Cascade(value = org.hibernate.annotations.CascadeType.ALL)
	private Integer customerKind;
	
//	@OneToOne
//	@JoinColumn(name="Account")
//	private CustomerRentalEntity rental;

	
	public CustomerRentalEntity(String account, String vin, Integer customerKind) {
		super();
		this.account = account;
		this.vin = vin;
		this.customerKind = customerKind;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public Integer getCustomerKind() {
		return customerKind;
	}

	public void setCustomerKind(Integer customerKind) {
		this.customerKind = customerKind;
	} 
	
	
	
}
