package org.serratec.backend.redesocial.controller;

import java.net.URI;
import java.util.List;

import org.serratec.backend.redesocial.dto.UserDTO;
import org.serratec.backend.redesocial.dto.UserInserirDTO;
import org.serratec.backend.redesocial.model.User;
import org.serratec.backend.redesocial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping
	public ResponseEntity<List<UserDTO>> listar() {
		return ResponseEntity.ok(userService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> buscar(@PathVariable Long id) {
		return ResponseEntity.ok(userService.findById(id));

	}

	@PostMapping
	public ResponseEntity<UserDTO> inserir(@RequestBody UserInserirDTO userInserirDTO) {
		UserDTO userDTO = userService.inserir(userInserirDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userDTO.getId())
				.toUri();
		return ResponseEntity.created(uri).body(userDTO);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleter (@PathVariable Long id){
		 userService.delete(id);
		 return ResponseEntity.noContent().build();
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<UserDTO> alterar(@PathVariable Long id, @RequestBody UserInserirDTO novaInfoUser) {
		return ResponseEntity.ok(userService.save(id, novaInfoUser));
	}

}
