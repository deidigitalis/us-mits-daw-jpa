package entity;

import java.io.Serializable;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * To create ID generator sequence "DEPARTAMENTO_ID_SEQ_GEN":
 * CREATE SEQUENCE "DEPARTAMENTO_ID_SEQ_GEN" INCREMENT BY 50 START WITH 50;
 */
@Entity
@NamedQueries({ @NamedQuery(name = "AFMDepartamento.findAll", query = "select o from AFMDepartamento o") })
@SequenceGenerator(name = "Departamento_Id_Seq_Gen", sequenceName = "DEPARTAMENTO_ID_SEQ_GEN", allocationSize = 50,
                   initialValue = 50)
@Table(name = "AFMDEPARTAMENTO")
public class AFMDepartamento implements Serializable {
    /**
     * Serialization version
     * @see https://stackoverflow.com/a/285809
     */
    @SuppressWarnings("compatibility:2114598268333356701")
    private static final long serialVersionUID = -2170823298964929975L;

    /**
     * Identificador
     */
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Departamento_Id_Seq_Gen")
    @Id
    private Integer id;

    /**
     * Versión. Entity manager lo utiliza para detectar actualizaciones conflictivas
     */
    @Version
    private Integer version;

    /**
     * Nombre
     */
    private String nombre;

    /**
     * Descripción
     */
    private String descripcion;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "departamento")
    private List<AFMEmpleado> empleados;

    public AFMDepartamento() {
    }

    /**
     * Propiedad obtiene el identificador
     * @return El identificador
     */
    public Integer getId() {
        return id;
    }

    /**
     * Propiedad fija el identificador
     * @param id El nuevo identificador
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre
     * @return El nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Fija el nombre
     * @param nombre El nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la descripción
     * @return La descripción
     */
    public String getDescripcion() {
        return this.descripcion;
    }

    /**
     * Fija la descripción
     * @param descripcion La nueva descripción
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el listado de empleados
     * @return Lista de empleados
     */
    public List<AFMEmpleado> getEmpleados() {
        return empleados;
    }

    /**
     * Fija el listado de empleados
     * @param empleados Nuevo listado de empleados
     */
    public void setEmpleados(List<AFMEmpleado> empleados) {
        this.empleados = empleados;
    }

    /**
     * Obtiene la versión
     * @return
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * Fija la versión
     * @param version Nueva versión
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("Id: ");
        buffer.append(getId());
        buffer.append("Nombre: ");
        buffer.append(getNombre());
        buffer.append("Descripción: ");
        buffer.append(getDescripcion());
        return buffer.toString();
    }
}
