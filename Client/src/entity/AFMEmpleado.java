package entity;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.Version;

@Entity
@NamedQueries({ @NamedQuery(name = "AFMEmpleado.findAll", query = "select o from AFMEmpleado o"),
                @NamedQuery(name = "AFMEmpleado.findAllOrderBySalary",
                            query = "select o from AFMEmpleado o order by salario desc")
    })
public class AFMEmpleado implements Serializable {
    private static final long serialVersionUID = 3899950953404950296L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Version
    private Integer version;

    private String apellido;

    private String nombre;

    @OneToOne(cascade = CascadeType.ALL)
    private AFMSeguroSanitario seguroSanitario;

    @Transient
    private String nombreCompleto;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "empleado")
    private List<AFMNumeroTelefono> telefonos;

    @ManyToMany
    private List<AFMProyecto> proyectos = new ArrayList<AFMProyecto>();

    @Temporal(javax.persistence
                   .TemporalType
                   .DATE)
    private Date fechaalta;

    private Long salario;

    /**
     * AFMDepartamento del empleado
     */
    @ManyToOne
    private AFMDepartamento departamento;

    public AFMEmpleado() {
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public AFMSeguroSanitario getSeguroSanitario() {
        return seguroSanitario;
    }

    public void setSeguroSanitario(AFMSeguroSanitario seguroSanitario) {
        this.seguroSanitario = seguroSanitario;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public List<AFMNumeroTelefono> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(List<AFMNumeroTelefono> telefonos) {
        this.telefonos = telefonos;
    }

    public List<AFMProyecto> getProyectos() {
        return proyectos;
    }

    public void setProyectos(List<AFMProyecto> proyectos) {
        this.proyectos = proyectos;
    }

    public Date getFechaalta() {
        return fechaalta;
    }

    public void setFechaalta(Date fechaalta) {
        this.fechaalta = fechaalta;
    }

    public Long getSalario() {
        return salario;
    }

    public void setSalario(Long salario) {
        this.salario = salario;
    }

    /**
     * Obtiene el departamento del empleado
     * @return El departamento
     */
    public AFMDepartamento getDepartamento() {
        return this.departamento;
    }

    /**
     * Fija el departamento del empleado
     * @param departamento El nuevo departamento
     */
    public void setDepartamento(AFMDepartamento departamento) {
        this.departamento = departamento;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("Id: ");
        buffer.append(getId());
        buffer.append(" ");
        buffer.append("Salario: ");
        buffer.append(getSalario());
        buffer.append(" ");
        buffer.append("Nombre: ");
        buffer.append(getNombre());
        buffer.append(" ");
        buffer.append("Apellidos: ");
        buffer.append(getApellido());
        buffer.append(" ");
        buffer.append("Fecha de alta: ");
        buffer.append(getFechaalta());
        buffer.append(" ");
        buffer.append("Nombre Completo: ");
        buffer.append(getNombreCompleto());
        buffer.append(" ");
        buffer.append("Teléfonos: ");
        buffer.append(getTelefonos());
        buffer.append(" ");
        buffer.append("Departamento: ");
        buffer.append(getDepartamento());

        return buffer.toString();
    }
}
