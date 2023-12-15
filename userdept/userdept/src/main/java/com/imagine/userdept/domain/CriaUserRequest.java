package com.imagine.userdept.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CriaUserRequest {

	private String name;

	private String email;
	
	private Boolean admin = Boolean.FALSE;

	private String department;
}
