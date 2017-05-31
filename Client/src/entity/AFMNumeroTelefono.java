package entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class AFMNumeroTelefono implements Serializable {
    private static final long serialVersionUID = 1L;

  @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String prefijoInternacional;
    private String numeroLocal;
    
    
    @ManyToOne
    private AFMEmpleado empleado;
    
    public void setPrefijoInternacional(String prefijoInternacional) {
        this.prefijoInternacional = prefijoInternacional;
    }

    public String getPrefijoInternacional() {
        return prefijoInternacional;
    }

    public void setNumeroLocal(String numeroLocal) {
        this.numeroLocal = numeroLocal;
    }

    public String getNumeroLocal() {
        return numeroLocal;
    }


    public void setEmpleado(AFMEmpleado empleado) {
        this.empleado = empleado;
    }

    public AFMEmpleado getEmpleado() {
        return empleado;
    }
}


