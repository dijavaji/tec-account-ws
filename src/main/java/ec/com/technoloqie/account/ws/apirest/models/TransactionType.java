package ec.com.technoloqie.account.ws.apirest.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="TRANSACTIONTYPE")
public class TransactionType implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name="TRATYPID",nullable=false, unique=true)
	private Integer id;
	
	@Column(name="NAME",nullable=false, unique=true)
    private String name;
	
	@Column(name="DESCRIPTION")
    private String description;
	
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
	
	private static final long serialVersionUID = 1L;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	
}
