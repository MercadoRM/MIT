package mx.mimr.demo1.rest;

import org.slf4j.Logger;
// https://www.adictosaltrabajo.com/2013/09/10/slf-4j/
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;


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
	public ResponseEntity<UserDTO> crearUsuario( @Valid @RequestBody final UserDTO usuario) // Convierte el contenido de la petición POST a un tipo de UserDTO
	{
		logger.info("Creating User : {}", usuario);
		System.out.println("El usuario recibido es:");
		System.out.println(usuario);
		ur.save(usuario);
		return new ResponseEntity<UserDTO>(usuario, HttpStatus.CREATED);
	}
	
	@GetMapping( value = "/{id}")
	public ResponseEntity<UserDTO> mostrarUsuario(@PathVariable("id") final Long id)
	{
		Optional<UserDTO> usuario = ur.findById(id);
		if(usuario.isPresent())
		{
			System.out.println("El usuario solicitado es:");
			System.out.println(usuario.get());
			return new ResponseEntity<UserDTO>(usuario.get(), HttpStatus.OK);
		}
		return new ResponseEntity<UserDTO>( new CustomErrorType("El usuario "+
				id + " no fue encontrado") , HttpStatus.NOT_FOUND);
	}
	
	//Un atajo para @RequestMapping(method = RequestMethod.PUT)
	@PutMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> actualizarUsuario( 
			@PathVariable("id") final Long id,@RequestBody UserDTO nuevo)
	{
		Optional<UserDTO> actual = ur.findById(id);
		if(actual.isPresent())
		{
			actual.get().setNombre(nuevo.getNombre());
			actual.get().setDireccion(nuevo.getDireccion());
			actual.get().setEmail(nuevo.getEmail());
			ur.saveAndFlush(actual.get());
			return new ResponseEntity<UserDTO>(actual.get(), HttpStatus.OK);
		}
		return new ResponseEntity<UserDTO>( new CustomErrorType("No se puede actualizar."
				+ "El usuario "+ id +" no fue encontrado."), HttpStatus.NOT_FOUND);
	}

	// Shortcut for  @RequestMapping(method = RequestMethod.DELETE)
	@DeleteMapping("/{id}")
	public ResponseEntity<UserDTO> borrarUsuario( @PathVariable("id") final Long id)
	{
		if( ur.findById(id).get() == null)
		{
			return new ResponseEntity<UserDTO>( new CustomErrorType("No se pudo eliminar."
					+ "El usuario "+ id +" no fue encontrado."), HttpStatus.NOT_FOUND);
		}
		ur.deleteById(id);
		return new ResponseEntity<UserDTO>(HttpStatus.NO_CONTENT);
	}
	
}
