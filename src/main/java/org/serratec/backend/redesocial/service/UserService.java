package org.serratec.backend.redesocial.service;

import java.util.List;
import java.util.Optional;

import org.serratec.backend.redesocial.exception.EmailException;
import org.serratec.backend.redesocial.exception.NotFoundException;
import org.serratec.backend.redesocial.model.User;
import org.serratec.backend.redesocial.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findById(Long id) throws NotFoundException {
		Optional<User> userOpt = userRepository.findById(id);
		if (userOpt.isEmpty()) {
			throw new NotFoundException();
		}
		return userOpt.get();
	}

	public User inserir(User u) throws EmailException {
		User user = userRepository.findByEmail(u.getEmail());
		if (user != null) {
			throw new EmailException("Email já existente");
		}
		return userRepository.save(u);
	}

}
