package entity;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.Version;

@Entity
@NamedQueries({ @NamedQuery(name = "AFMEmpleado.findAll", query = "select o from AFMEmpleado o"), @NamedQuery(name = "AFMEmpleado.findAllOrderBySalary", query = "select o from AFMEmpleado o order by salario desc") })
public class AFMEmpleado implements Serializable {
    private static final long serialVersionUID = 3899950953404950296L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) 
    private Integer id;

    @Version
    private Integer version;
    
    
    private String apellido;
    private String nombre;
    
    @OneToOne(cascade=CascadeType.ALL)
    private AFMSeguroSanitario seguroSanitario;

    public void setSeguroSanitario(AFMSeguroSanitario seguroSanitario) {
        this.seguroSanitario = seguroSanitario;
    }

    public AFMSeguroSanitario getSeguroSanitario() {
        return seguroSanitario;
    }

    @Transient
    private String nombreCompleto;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "empleado")
    private List<AFMNumeroTelefono> telefonos;
    
    @ManyToMany
    private List<AFMProyecto> proyectos = new ArrayList<AFMProyecto>();

    public void setProyectos(List<AFMProyecto> proyectos) {
        this.proyectos = proyectos;
    }

    public List<AFMProyecto> getProyectos() {
        return proyectos;
    }

    public void setTelefonos(List<AFMNumeroTelefono> telefonos) {
        this.telefonos = telefonos;
    }

    public List<AFMNumeroTelefono> getTelefonos() {
        return telefonos;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaalta;

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getApellido() {
        return apellido;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setFechaalta(Date fechaalta) {
        this.fechaalta = fechaalta;
    }

    public Date getFechaalta() {
        return fechaalta;
    }

    public void setSalario(Long salario) {
        this.salario = salario;
    }

    public Long getSalario() {
        return salario;
    }
    private Long salario;



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
    
    public String toString(){
        return "Id: "+ id + " Salario: "+ salario + " Nombre: "+nombre + " Apellidos: "+ apellido + " Fecha de alta: "+fechaalta + " Nombre Completo: "+nombreCompleto /*+"\nTelefonos: "+telefonos + 
            " SeguroSanitario:" + seguroSanitario*/;
    }
}
