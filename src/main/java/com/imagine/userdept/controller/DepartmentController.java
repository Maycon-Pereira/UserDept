package com.imagine.userdept.controller;

import java.util.List;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.imagine.userdept.domain.CriaDepartmentRequest;
import com.imagine.userdept.domain.CriaDepartmentResponse;
import com.imagine.userdept.domain.DeletaDepartmentResponse;
import com.imagine.userdept.entity.DepartmentEntity;
import com.imagine.userdept.service.DepartmentService;

@RestController
@RequestMapping("/department")
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;
	
	@PostMapping
	public CriaDepartmentResponse criaDepartment(@RequestBody CriaDepartmentRequest request ) {
		
		CriaDepartmentResponse response = departmentService.criaDepartment(request);
		return response;
	}
	
	@GetMapping
	public List<DepartmentEntity> listaDepartment() {
		
		List<DepartmentEntity> response = departmentService.listaDepartment();
		return response;
		
	}
	
	@GetMapping("/{id}")
	public DepartmentEntity buscaDepartmentPorId(@PathVariable String id) {
		
		DepartmentEntity response = departmentService.buscaDepartmentPorId(id);
		return response;
	}
	
	@PutMapping("/{id}")
	public DepartmentEntity atualizaDepartment(@PathVariable String id, @RequestBody CriaDepartmentRequest request) throws Exception {
		
		DepartmentEntity response = departmentService.atualizaDepartment(id, request);
		if(response == null) {
			throw new AccountNotFoundException("Id não encontrado na base");
		}
		return response;
	}
	
	@PatchMapping("/public/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void mPublic(@PathVariable String id) {
		departmentService.mPublic(id);
	}
	@PatchMapping("/private/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void mPrivate(@PathVariable String id) {
		departmentService.mPrivate(id);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<DeletaDepartmentResponse> removeDepartment(@PathVariable String id) {
		
		DeletaDepartmentResponse response = departmentService.removeDepartment(id);
		
		if(response.isDeletado()) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	
	
	
}
