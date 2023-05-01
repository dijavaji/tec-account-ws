package ec.com.technoloqie.account.ws.apirest.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="ACCOUNT")
public class Account implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name="ACCOUNTID",nullable=false, unique=true)
	private Integer id;
	
	@Column(name="ACCNUMBER",nullable=false, unique=true)
    private Integer accNumber; 
	
	@Column(name="BALANCE",nullable=false)
	private Double balance;
	
	@NotEmpty(message ="no puede estar vacio")
	@Column(name="CREATEDBY",nullable=false)
	private String createdBy;
	
	@Column(name="CREATEDDATE",nullable=false)
	@Temporal(TemporalType.DATE)
	private Date createdDate;
	
	@Column(name="MODIFIEDBY")
	private String modifiedBy;
	
	@Column(name="MODIFIEDDATE")
	@Temporal(TemporalType.DATE)
	private Date modifiedDate;
	
	@Column(name="STATUS")
	private Boolean status;
	
	@ManyToOne
	@JoinColumn(name="ACCTYPID",nullable=false)
	private AccountType accountType;
	
    @PrePersist 
	public void prePersist() {
		createdDate = new Date();
		status = Boolean.TRUE;
	}

	private static final long serialVersionUID = -979071489122757786L;

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	public Double getBalance() {
		return balance;
	}


	public void setBalance(Double balance) {
		this.balance = balance;
	}


	public String getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	public Date getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


	public String getModifiedBy() {
		return modifiedBy;
	}


	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}


	public Date getModifiedDate() {
		return modifiedDate;
	}


	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}


	public Boolean getStatus() {
		return status;
	}


	public void setStatus(Boolean status) {
		this.status = status;
	}


	public Integer getAccNumber() {
		return accNumber;
	}


	public void setAccNumber(Integer accNumber) {
		this.accNumber = accNumber;
	}


	public AccountType getAccountType() {
		return accountType;
	}


	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
}
