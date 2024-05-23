package org.serratec.backend.redesocial.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")

public class CommentController {
	@PostMapping
	public String createComment() {
		return "Teste";
	}

}
