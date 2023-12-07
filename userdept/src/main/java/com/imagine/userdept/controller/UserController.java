package com.imagine.userdept.controller;

import java.util.List;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imagine.userdept.domain.CriaUserRequest;
import com.imagine.userdept.domain.CriaUserResponse;
import com.imagine.userdept.domain.DeletaUserResponse;
import com.imagine.userdept.entity.UserEntity;
import com.imagine.userdept.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public CriaUserResponse criaUser(@RequestBody CriaUserRequest request ) {
		
		CriaUserResponse response = userService.criaUser(request);
		return response;
	}
	
	@GetMapping
	public List<UserEntity> listaUser() {
		
		List<UserEntity> response = userService.listaUser();
		return response;
		
	}
	
	@GetMapping("/{id}")
	public UserEntity buscaUserPorId(@PathVariable String id) {
		
		UserEntity response = userService.buscaUserPorId(id);
		return response;
	}
	
	@PutMapping("/{id}")
	public UserEntity atualizaUser(@PathVariable String id, @RequestBody CriaUserRequest request) throws Exception {
		
		UserEntity response = userService.atualizaUser(id, request);
		if(response == null) {
			throw new AccountNotFoundException("Id não encontrado na base");
		}
		return response;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<DeletaUserResponse> removeUser(@PathVariable String id) {

		DeletaUserResponse response = userService.removeUser(id);
		
		if(response.isDeletado()) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	
}
