package com.imagine.userdept.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CriaDepartmentRequest {

	private String name;
	
	private Boolean visibility = Boolean.FALSE;
}
