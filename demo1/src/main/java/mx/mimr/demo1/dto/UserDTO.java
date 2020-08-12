package mx.mimr.demo1.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/* Data transfer object: Contains only data and access modifiers,
 * but no logic. It's used to transfer data between layers
 * https://docs.oracle.com/cd/A97335_02/apps.102/bc4j/developing_bc_projects/bc_awhatisaneo.htm
 */
@Entity // This makes the object an jpa entity
@Table(name = "USUARIO") // One table may be represented by more than one entity object. 
//Table annotation defines a table 
public class UserDTO {
	/* For example, an Items table might store inventory items,
	 *  requisition items, order items, and so on, which are different "entity types."
	 */
	@Id //Indicates the attribute as primary key
	@GeneratedValue
	@Column(name = "ID_USUARIO") //Details of the column it'll be mapped
	private Long id;
	@Column(name = "NOMBRE_USUARIO")
	private String nombre;
	@Column(name = "DIRECCION_USUARIO")
	private String direccion;
	@Column(name = "EMAIL_USUARIO")
	private String email;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getDireccion() {
		return direccion;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	@Override
	public String toString()
	{
		return "Mi id es: " + id
				+ "\n Mi nombre es: " + nombre 
				+ "\n Mi direccion es:" + direccion
				+ "\n Mi correo es:" + email;
	}
	
}
