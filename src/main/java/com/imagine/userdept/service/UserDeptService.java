package com.imagine.userdept.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imagine.userdept.domain.CriaUserDeptRequest;
import com.imagine.userdept.domain.CriaUserDeptResponse;
import com.imagine.userdept.entity.DepartmentEntity;
import com.imagine.userdept.entity.UserDeptEntity;
import com.imagine.userdept.entity.UserEntity;
import com.imagine.userdept.repository.DepartmentRepository;
import com.imagine.userdept.repository.UserDeptRepository;
import com.imagine.userdept.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserDeptService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private UserDeptRepository userDeptRepository;

	public CriaUserDeptResponse criaConexao(CriaUserDeptRequest request) {

		Optional<UserEntity> userProcurado = userRepository.findById(request.getUser_id());
		if (!userProcurado.isPresent()) {
			throw new EntityNotFoundException("Usuario não encontrado");
		}
		Optional<DepartmentEntity> deptProcurado = departmentRepository.findById(request.getDepartment_id());
		if (!deptProcurado.isPresent()) {
			throw new EntityNotFoundException("Departamento não encontrado");
		}
		
//		....
		
		UserEntity userEntity = userProcurado.get();
		DepartmentEntity departmentEntity = deptProcurado.get();
		
		userEntity.setDepartment(departmentEntity.getName());
		userRepository.save(userEntity);

		UserDeptEntity userDeptEntity = new UserDeptEntity();
		userDeptEntity.setId(UUID.randomUUID().toString());
		userDeptEntity.setUser_id(request.getUser_id());
		userDeptEntity.setDepartment_id(request.getDepartment_id());

		UserDeptEntity saved = userDeptRepository.save(userDeptEntity);

		CriaUserDeptResponse response = new CriaUserDeptResponse();
		response.setId(saved.getId());

		return response;
	}

	public List<UserDeptEntity> listaTodosUserDept() {

		List<UserDeptEntity> response = userDeptRepository.findAll();

		return response;
	}

	

}
