package mx.mimr.demo1.rest;

import org.slf4j.Logger;
// https://www.adictosaltrabajo.com/2013/09/10/slf-4j/
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import mx.mimr.demo1.dao.*;
import mx.mimr.demo1.dto.*;

@RestController
@RequestMapping("/api/user/")
public class UserREST {

	public static final Logger logger =
			LoggerFactory.getLogger(UserREST.class);
	
	private UserRepository ur;
	
	@Autowired
	public void setUserRepository(UserRepository ur) {
		this.ur = ur;
	}
	
	// Shortcut for @RequestMapping(value="/", method = RequestMethod.GET)
	@GetMapping("/") //Entidad o respuesta HTTP
	public ResponseEntity<List<UserDTO>> mostrarTodos()
	{
		List<UserDTO> usuarios = ur.findAll();
		return new ResponseEntity<List<UserDTO>>(usuarios,HttpStatus.OK);
	}
	
	@PostMapping( value = "/", consumes = MediaType.APPLICATION_JSON_VALUE) 
	public ResponseEntity<UserDTO> crearUsuario(@RequestBody final UserDTO usuario) // Convierte el contenido de la petición POST a un tipo de UserDTO
	{
		ur.save(usuario);
		return new ResponseEntity<UserDTO>(usuario, HttpStatus.CREATED);
	}
}
