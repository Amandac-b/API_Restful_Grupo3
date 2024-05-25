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

import jakarta.transaction.Transactional;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	// buscar todos
	public List<UserDTO> findAll() {
		List<User> users = userRepository.findAll();

		List<UserDTO> userDTO = new ArrayList<>();
		for (User user : users) {
			userDTO.add(new UserDTO(user));
		}

		return userDTO;
	}

	// buscar um
	public User findById(Long id) throws NotFoundException {
		Optional<User> userOpt = userRepository.findById(id);
		if (userOpt.isEmpty()) {
			throw new NotFoundException();
		}
		return userOpt.get();
	}

	// inserir
	@Transactional
	public UserDTO inserir(UserInserirDTO userInserirDTO) throws EmailException, SenhaException {
		if (!userInserirDTO.getSenha().equalsIgnoreCase(userInserirDTO.getConfirmaSenha())) {
			throw new SenhaException("Senha e Confirma Senha não são iguais");
		}
		User userBd = userRepository.findByEmail(userInserirDTO.getEmail());
		if (userBd != null) {
			throw new EmailException("Email ja existente");
		}

		return new UserDTO(userRepository.save(new User(userInserirDTO)));
	}

	// deletar
	public void delete(Long id) {
		Optional<User> userOpt = userRepository.findById(id);
		
		if (userOpt.isEmpty()) {
			throw new NotFoundException ("Nenhum item foi encontrado correspondente ao id fornecido");
		}
		userRepository.deleteById(id);
	}

	// atualizar
    public UserDTO save(Long Id, UserInserirDTO novoUserDTO) {
        Optional<User> userOpt = userRepository.findById(Id);

        if (userOpt.isPresent ()) {
            User novoUser = new User(novoUserDTO);
            novoUser.setId(Id);
            return new UserDTO(userRepository.save(novoUser));
        }

        throw new NotFoundException();
    }
}
