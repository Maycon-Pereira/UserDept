package com.imagine.userdept.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="Users")
@Table(name="Users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

	@Id
	private String id;
	
	private String name;
	
	private String email;
	
	private Boolean admin = Boolean.FALSE;
	
//	@ManyToOne
//	@JoinColumn(name = "department_id")
	private String department;
	
	
	public void active() {
		setAdmin(true);
	}
	public void inactive() {
		setAdmin(false);
	}
}
