package br.com.fiap.iDoei.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Food {
	@Id @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String type;
	private Long amount;
	private Boolean isSelected;
	private Long personId;
}
