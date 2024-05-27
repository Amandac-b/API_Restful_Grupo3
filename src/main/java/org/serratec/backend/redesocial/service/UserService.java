package org.serratec.backend.redesocial.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.serratec.backend.redesocial.dto.RelationshipDTO;
import org.serratec.backend.redesocial.dto.UserDTO;
import org.serratec.backend.redesocial.dto.UserInserirDTO;
import org.serratec.backend.redesocial.exception.EmailException;
import org.serratec.backend.redesocial.exception.NoContentException;
import org.serratec.backend.redesocial.exception.NotFoundException;
import org.serratec.backend.redesocial.exception.SenhaException;
import org.serratec.backend.redesocial.model.Relationship;
import org.serratec.backend.redesocial.model.RelationshipPK;
import org.serratec.backend.redesocial.model.User;
import org.serratec.backend.redesocial.repository.RelationshipRepository;
import org.serratec.backend.redesocial.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RelationshipRepository relationshipRepository;

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
			throw new NotFoundException("Nenhum item foi encontrado correspondente ao id fornecido");
		}
		userRepository.deleteById(id);
	}

	// atualizar
	public UserDTO save(Long Id, UserInserirDTO novoUserDTO) {
		Optional<User> userOpt = userRepository.findById(Id);

		if (userOpt.isPresent()) {
			User novoUser = new User(novoUserDTO);
			novoUser.setId(Id);
			return new UserDTO(userRepository.save(novoUser));
		}

		throw new NotFoundException();
	}

	// MÉTODO DE RELACIONAMENTO

	
	public RelationshipDTO seguir(Long idSeguido, Long idSeguidor) {
		
		Optional<User> seguidoOpt = userRepository.findById(idSeguido);
		Optional<User> seguidorOpt = userRepository.findById(idSeguidor);

		if (seguidoOpt.isPresent() && seguidorOpt.isPresent()) {
			RelationshipPK chave = new RelationshipPK (seguidoOpt.get(), seguidorOpt.get());
			Relationship novoSeguidor = new Relationship(chave, LocalDate.now());
			
			return new RelationshipDTO (relationshipRepository.save(novoSeguidor));
		
		}
		throw new NotFoundException();
		
	}
	
	
	public List<RelationshipDTO> findAllFollowingById(Long id) throws NotFoundException, NoContentException {

		Optional<User> userOpt = userRepository.findById(id);

		if (userOpt.isPresent()) {
			List<Relationship> relationships = relationshipRepository.findAllFollowingById(id);
			List<RelationshipDTO> relationshipsDTO = new ArrayList<>();

			for (Relationship relationship : relationships) {
				RelationshipDTO relationshipDTO = new RelationshipDTO(relationship);
				relationshipsDTO.add(relationshipDTO);
			}

			if (!relationshipsDTO.isEmpty()) {
				return relationshipsDTO;
			}

			throw new NoContentException();
		}

		throw new NotFoundException("Este Usuário não tem seguimentos.");
	}

	public List<RelationshipDTO> findAllFollowersByUserId(Long id) throws NotFoundException, NoContentException {

		Optional<User> userOpt = userRepository.findById(id);

		if (userOpt.isPresent()) {
			List<Relationship> relationships = relationshipRepository.findAllFollowersByUserId(id);
			List<RelationshipDTO> relationshipsDTO = new ArrayList<>();

			for (Relationship relationship : relationships) {
				RelationshipDTO relationshipDTO = new RelationshipDTO(relationship);
				relationshipsDTO.add(relationshipDTO);
			}

			if (!relationshipsDTO.isEmpty()) {
				return relationshipsDTO;
			}

			throw new NoContentException();
		}

		throw new NotFoundException("Nenhum seguidor foi encontrado para o Id solicitado.");
	}

	public void findAndDelete(Long idSeguidor, Long idSeguido) {
		Optional<User> userSeguidorOpt = userRepository.findById(idSeguidor);
		Optional<User> userSeguidoOpt = userRepository.findById(idSeguido);

		if (userSeguidorOpt.isPresent() && userSeguidoOpt.isPresent()) {

			if (relationshipRepository.findRelationshipById(idSeguidor, idSeguido).isEmpty()) {
				throw new NotFoundException("Não existe uma relação entre os Usuários.");
			}
			relationshipRepository.findAndDelete(idSeguidor, idSeguido);
		} else {
			throw new RuntimeException("Usuário não encontrado com o ID fornecido.");
		}
	}

		throw new NotFoundException();
	}

	// MÉTODO DE RELACIONAMENTO

	
	public RelationshipDTO seguir(Long idSeguido, Long idSeguidor) {
		
		Optional<User> seguidoOpt = userRepository.findById(idSeguido);
		Optional<User> seguidorOpt = userRepository.findById(idSeguidor);

		if (seguidoOpt.isPresent() && seguidorOpt.isPresent()) {
			RelationshipPK chave = new RelationshipPK (seguidoOpt.get(), seguidorOpt.get());
			Relationship novoSeguidor = new Relationship(chave, LocalDate.now());
			
			return new RelationshipDTO (relationshipRepository.save(novoSeguidor));
		
		}
		throw new NotFoundException();
		
	}
	
	
	public List<RelationshipDTO> findAllFollowingById(Long id) throws NotFoundException, NoContentException {

		Optional<User> userOpt = userRepository.findById(id);

		if (userOpt.isPresent()) {
			List<Relationship> relationships = relationshipRepository.findAllFollowingById(id);
			List<RelationshipDTO> relationshipsDTO = new ArrayList<>();

			for (Relationship relationship : relationships) {
				RelationshipDTO relationshipDTO = new RelationshipDTO(relationship);
				relationshipsDTO.add(relationshipDTO);
			}

			if (!relationshipsDTO.isEmpty()) {
				return relationshipsDTO;
			}

			throw new NoContentException();
		}

		throw new NotFoundException("Este Usuário não tem seguimentos.");
	}

	public List<RelationshipDTO> findAllFollowersByUserId(Long id) throws NotFoundException, NoContentException {

		Optional<User> userOpt = userRepository.findById(id);

		if (userOpt.isPresent()) {
			List<Relationship> relationships = relationshipRepository.findAllFollowersByUserId(id);
			List<RelationshipDTO> relationshipsDTO = new ArrayList<>();

			for (Relationship relationship : relationships) {
				RelationshipDTO relationshipDTO = new RelationshipDTO(relationship);
				relationshipsDTO.add(relationshipDTO);
			}

			if (!relationshipsDTO.isEmpty()) {
				return relationshipsDTO;
			}

			throw new NoContentException();
		}

		throw new NotFoundException("Nenhum seguidor foi encontrado para o Id solicitado.");
	}

	public void findAndDelete(Long idSeguidor, Long idSeguido) {
		Optional<User> userSeguidorOpt = userRepository.findById(idSeguidor);
		Optional<User> userSeguidoOpt = userRepository.findById(idSeguido);

		if (userSeguidorOpt.isPresent() && userSeguidoOpt.isPresent()) {

			if (relationshipRepository.findRelationshipById(idSeguido, idSeguidor).isEmpty()) {
				throw new NotFoundException("Não existe uma relação entre os Usuários.");
			}
			relationshipRepository.findAndDelete(idSeguido, idSeguidor);
		} else {
			throw new RuntimeException("Usuário não encontrado com o ID fornecido.");
		}
	}

}
