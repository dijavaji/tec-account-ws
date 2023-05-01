package ec.com.technoloqie.account.ws.apirest.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 
 * @author root
 *
 */
@Entity
@Table(name="PERSON")
public class Person implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PERSONID",nullable=false, unique=true)
	private Integer id;
	
	@NotEmpty(message ="no puede estar vacio")
	@NotNull(message= "no puede estar vacio")
	@Column(name="FIRSTNAME",nullable=false)
	private String firstName; //atributo nombre
	 
	@NotEmpty(message ="no puede estar vacio")
	@Column(name="LASTNAME")
	private String lastName; //atributo apellido
	
	@Column(name="GENDER")
	private String gender;
	
	@Column(name="AGE")
	private Integer age;
	
	@Column(name="IDN")
	private String idn;// atributo identificacion
	
	@Column(name="ADDRESS")
	private String address;// atributo direccion
	
	@Column(name="PHONE",nullable=false)
	private String phone;// telefono
	
	
	@Email(message="no es una direccion de correo bien formada")
	@Column(name="EMAIL")
	private String email;// email
	
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
	
	//@OneToOne(mappedBy = "person")
    //private Customer customer;
	
	@PrePersist 
	public void prePersist() {
		createdDate = new Date();
		status = Boolean.TRUE;
	}
	
	private static final long serialVersionUID = 8505762287691021184L;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getIdn() {
		return idn;
	}

	public void setIdn(String idn) {
		this.idn = idn;
	}

}
