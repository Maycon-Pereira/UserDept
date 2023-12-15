package com.imagine.userdept.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imagine.userdept.domain.CriaDepartmentRequest;
import com.imagine.userdept.domain.CriaDepartmentResponse;
import com.imagine.userdept.domain.DeletaDepartmentResponse;
import com.imagine.userdept.entity.DepartmentEntity;
import com.imagine.userdept.repository.DepartmentRepository;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;

	public CriaDepartmentResponse criaDepartment(CriaDepartmentRequest request) {

		DepartmentEntity departmentEntity = new DepartmentEntity();

		departmentEntity.setId(UUID.randomUUID().toString());
		departmentEntity.setName(request.getName());

		DepartmentEntity saved = departmentRepository.save(departmentEntity);

		CriaDepartmentResponse criaDepartmentResponse = new CriaDepartmentResponse();
		criaDepartmentResponse.setId(saved.getId());

		return criaDepartmentResponse;
	}

	public List<DepartmentEntity> listaDepartment() {

		List<DepartmentEntity> departments = departmentRepository.findAll();

		return departments;
	}

	public DepartmentEntity buscaDepartmentPorId(String id) {

		Optional<DepartmentEntity> procurado = departmentRepository.findById(id);
		if (procurado.isPresent()) {
			DepartmentEntity departmentEntity = procurado.get();
			return departmentEntity;
		} else {
			return null;
		}

	}

	public DepartmentEntity atualizaDepartment(String id, CriaDepartmentRequest request) {

		Optional<DepartmentEntity> procurado = departmentRepository.findById(id);
		if (!procurado.isPresent()) {
			return null;
		}
		DepartmentEntity departmentEntity = procurado.get();
		departmentEntity.setName(request.getName());

		DepartmentEntity saved = departmentRepository.save(departmentEntity);
		return saved;
	}

	public DeletaDepartmentResponse removeDepartment(String id) {

		DeletaDepartmentResponse deletaDepartmentResponse = new DeletaDepartmentResponse();
		DepartmentEntity procurado = this.buscaDepartmentPorId(id);
		if (procurado == null) {
			deletaDepartmentResponse.setMensagem("Departmento não encontrado");
			deletaDepartmentResponse.setDeletado(false);
			return deletaDepartmentResponse;
		} else {
			try {
				departmentRepository.deleteById(id);

				deletaDepartmentResponse.setMensagem("departmento removido com sucesso");
				deletaDepartmentResponse.setDeletado(true);
				return deletaDepartmentResponse;
			} catch (Exception erro) {
				throw new RuntimeException("Erro ao tentar remover um departamento");
			}
		}
	}

}
