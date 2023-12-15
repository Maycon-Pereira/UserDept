package com.imagine.userdept.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="UserDept")
@Table(name="UserDept")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDeptEntity {

	@Id
	private String id;
	private String user_id;
	private String department_id;
}
