package com.imagine.userdept.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imagine.userdept.domain.CriaUserDeptRequest;
import com.imagine.userdept.domain.CriaUserDeptResponse;
import com.imagine.userdept.entity.UserDeptEntity;
import com.imagine.userdept.service.UserDeptService;

@RestController
@RequestMapping("/conect")
public class UserDeptController {

	@Autowired
	private UserDeptService userDeptService;
	
	@PostMapping("/criarConexao")
	public CriaUserDeptResponse criaConexao(@RequestBody CriaUserDeptRequest request) {
		
		CriaUserDeptResponse response = userDeptService.criaConexao(request);
		
		return response;
	}
	
	@GetMapping("/listarConexoes")
	public List<UserDeptEntity> listaTodosUserDept() {
		
		List<UserDeptEntity> response = userDeptService.listaTodosUserDept();
		return response;
	}
		
}
