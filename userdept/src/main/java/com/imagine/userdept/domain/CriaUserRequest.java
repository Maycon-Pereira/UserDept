package com.imagine.userdept.domain;

import com.imagine.userdept.entity.DepartmentEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CriaUserRequest {

	private String name;

	private String email;

	private DepartmentEntity department;
}
