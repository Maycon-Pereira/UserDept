package com.imagine.userdept.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imagine.userdept.domain.CriaUserRequest;
import com.imagine.userdept.domain.CriaUserResponse;
import com.imagine.userdept.domain.DeletaUserResponse;
import com.imagine.userdept.entity.UserEntity;
import com.imagine.userdept.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public CriaUserResponse criaUser(CriaUserRequest request) {

		UserEntity userEntity = new UserEntity();
		userEntity.setId(UUID.randomUUID().toString());
		userEntity.setName(request.getName());
		userEntity.setEmail(request.getEmail());
		userEntity.setAdmin(request.getAdmin());
		userEntity.setDepartment(request.getDepartment());

		UserEntity saved = userRepository.save(userEntity);

		CriaUserResponse criaUserResponse = new CriaUserResponse();
		criaUserResponse.setId(saved.getId());

		return criaUserResponse;

	}

	public List<UserEntity> listaUser() {
		List<UserEntity> users = userRepository.findAll();

		return users;
	}

	public UserEntity buscaUserPorId(String id) {

		Optional<UserEntity> procurado = userRepository.findById(id);
		if (procurado.isPresent()) {
			UserEntity UserEntity = procurado.get();
			return UserEntity;
		} else {
			return null;
		}
	}

	public UserEntity atualizaUser(String id, CriaUserRequest request) {
		Optional<UserEntity> procurado = userRepository.findById(id);
		if (!procurado.isPresent()) {
			return null;
		}
		UserEntity UserEntity = procurado.get();
		UserEntity.setName(request.getName());
		UserEntity.setEmail(request.getEmail());

		UserEntity saved = userRepository.save(UserEntity);
		return saved;
	}
	
	public void active(String id) {
		Optional<UserEntity> procurado = userRepository.findById(id);
		
		UserEntity userEntity = procurado.get();
		userEntity.active();
		userRepository.save(userEntity);
	}
	
	public void inactive(String id) {
		Optional<UserEntity> procurado = userRepository.findById(id);
		
		UserEntity userEntity = procurado.get();
		userEntity.inactive();
		userRepository.save(userEntity);
	}

	public DeletaUserResponse removeUser(String id) {

		DeletaUserResponse deletaUserResponse = new DeletaUserResponse();
		UserEntity procurado = this.buscaUserPorId(id);
		if (procurado == null) {
			deletaUserResponse.setMensagem("Usuario não encontrado");
			deletaUserResponse.setDeletado(false);
			return deletaUserResponse;
		} else {
			try {
				userRepository.deleteById(id);

				deletaUserResponse.setMensagem("Usuario removido com sucesso");
				deletaUserResponse.setDeletado(true);
				return deletaUserResponse;
			} catch (Exception erro) {
				throw new RuntimeException("Erro ao tentar remover um usuario");
			}
		}
	}

}