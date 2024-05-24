package org.serratec.backend.redesocial.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.serratec.backend.redesocial.dto.UserDTO;
import org.serratec.backend.redesocial.dto.UserInserirDTO;
import org.serratec.backend.redesocial.exception.EmailException;
import org.serratec.backend.redesocial.exception.NotFoundException;
import org.serratec.backend.redesocial.exception.SenhaException;
import org.serratec.backend.redesocial.model.User;
import org.serratec.backend.redesocial.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<UserDTO> findAll() {
		List<User> users = userRepository.findAll();
		
		List<UserDTO> userDTO = new ArrayList<>();
		for (User user: users) {
			userDTO.add(new UserDTO(user)); 
		}
		
		return userDTO;
	}

	public User findById(Long id) throws NotFoundException {
		Optional<User> userOpt = userRepository.findById(id);
		if (userOpt.isEmpty()) {
			throw new NotFoundException();
		}
		return userOpt.get();
	}

	public UserDTO inserir(UserInserirDTO u) throws EmailException, SenhaException {
		if(!u.getSenha().equalsIgnoreCase(u.getConfirmaSenha())) {
			throw new SenhaException("Senha e Confirma Senha não são iguais");
		}
		User userDB = userRepository.findByEmail(u.getEmail());
		if (userDB != null) {
			throw new EmailException("Email já existente");
		}
		
		User user = new User();
		user.setNome(u.getNome());
		user.setEmail(u.getEmail());
		user.setSenha(u.getSenha());
		user = userRepository.save(user);
		
		return new UserDTO(user); 
	}

}
