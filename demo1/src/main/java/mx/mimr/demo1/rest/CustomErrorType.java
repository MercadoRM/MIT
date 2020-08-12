package mx.mimr.demo1.rest;

import mx.mimr.demo1.dto.UserDTO;

public class CustomErrorType extends UserDTO {
	private String mensajeError;
	
	public CustomErrorType(final String mensajeError) {
			this.mensajeError = mensajeError;
	}
	
	public String getMensajeError()
	{
		return mensajeError;
	}
}
