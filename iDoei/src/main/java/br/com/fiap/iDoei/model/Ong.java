package br.com.fiap.iDoei.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Ong {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long number;
	private String user;
	private String cnpj;
	private String fullAddress;
	private String name;
	private String address;
	private String complement;
	private String district;
	private String password;
	private String repeatPassword;
//	@ManyToMany(fetch= FetchType.EAGER)
//	private Collection<Role> roles;
//	private Boolean is_active;

}
