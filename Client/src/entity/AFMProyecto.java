package entity;

import java.io.Serializable;

import java.util.List;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Version;

@Entity
@NamedQueries({
  @NamedQuery(name = "AFMProyecto.findAll", query = "select o from AFMProyecto o")
})
public class AFMProyecto implements Serializable {
    @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    private String nombre;
    
    @ManyToMany(mappedBy="proyectos")
    Set<AFMEmpleado> empleados;
    
    @Version
    private Integer version;

    public AFMProyecto() {
    }

    public AFMProyecto(String nombre, Set<AFMEmpleado> empleados) {
        this.nombre = nombre;
        this.empleados = empleados;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public void setEmpleados(Set<AFMEmpleado> empleados) {
        this.empleados = empleados;
    }

    public Set<AFMEmpleado> getEmpleados() {
        return empleados;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
    
    public String toString() {
        return "ID: "+ id + " Nombre: "+nombre;
    }
    
    
}
