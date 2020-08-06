package mx.mimr.demo1.dao;

import mx.mimr.demo1.dto.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository // La superclase toma el objeto que manipulara y un identificador
public interface UserRepository extends JpaRepository<UserDTO, Long>{
	/* La firma hace algo como:
	 * select u from Usuarios, where u.nombre equals:name
	 */
	public abstract UserDTO findByNombre(String name);
	// Spring crea una implementacion de este metodo en tiempo de ejecución
}
