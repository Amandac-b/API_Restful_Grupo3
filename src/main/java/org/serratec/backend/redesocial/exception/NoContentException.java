package org.serratec.backend.redesocial.exception;

public class NoContentException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public NoContentException() {

	}

	public NoContentException(String message) {
		super(message);
	}
}

