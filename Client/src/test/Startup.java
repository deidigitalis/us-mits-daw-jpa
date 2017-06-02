package test;

import entity.AFMDepartamento;
import entity.AFMEmpleado;
import entity.AFMNumeroTelefono;
import entity.AFMProyecto;
import entity.AFMSeguroSanitario;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

public class Startup {
    public static void main(String[] args) {
        Startup startup = new Startup();
        startup.createData();
        AFMEmpleado e1 = startup.buscaEmpleadoPorId(1);
        System.out.println(e1);
        List<AFMEmpleado> empleados = startup.findAll();
        for (AFMEmpleado e : empleados) {
            System.out.println(e);
        }
        startup.cerrarConexiones();
    }

    @PersistenceUnit
    EntityManagerFactory emf;
    EntityManager em;

    public Startup() {
        super();
        emf = Persistence.createEntityManagerFactory("EmpleadoHibernate");
        em = emf.createEntityManager();
    }

    private List<AFMEmpleado> findAll() {
        Query q = em.createNamedQuery("AFMEmpleado.findAll");
        return q.getResultList();
    }

    private void createData() {
        em.getTransaction().begin();

        Set<AFMDepartamento> departamentos = crearDepartamentos();
        AFMDepartamento lsi = departamentos.stream()
                                           .filter(x -> x.getAbreviatura() == "LSI")
                                           .findFirst()
                                           .get();

        AFMEmpleado e = new AFMEmpleado();
        //e.setId(1);
        e.setApellido("Fernandez-Montes");
        e.setNombre("Alejandro");
        e.setSalario(1000L);
        e.setFechaalta(new Date());
        e.setTelefonos(crearTelefonos(e));
        e.setSeguroSanitario(crearSeguroSanitario("Sanitas"));
        e.setDepartamento(lsi);

        em.persist(e);

        AFMEmpleado e2 = new AFMEmpleado();
        //e2.setId(2);
        e2.setApellido("Torres");
        e2.setNombre("Jesus");
        e2.setSalario(2000L);
        e2.setFechaalta(new Date());
        e2.setTelefonos(crearTelefonos(e2));
        e2.setSeguroSanitario(crearSeguroSanitario("Asisa"));
        e2.setDepartamento(lsi);

        Set<AFMEmpleado> empleados = new HashSet<AFMEmpleado>();
        empleados.add(e);
        empleados.add(e2);

        crearProyectos(empleados);

        em.persist(e2);
        em.getTransaction().commit();
    }

    private void cerrarConexiones() {
        em.close();
        emf.close();
    }

    private List<AFMNumeroTelefono> crearTelefonos(AFMEmpleado dueño) {
        AFMNumeroTelefono tef1 = new AFMNumeroTelefono();
        tef1.setNumeroLocal("626215444");
        tef1.setPrefijoInternacional("+34");
        tef1.setEmpleado(dueño);

        AFMNumeroTelefono tef2 = new AFMNumeroTelefono();
        tef2.setNumeroLocal("954458033");
        tef2.setPrefijoInternacional("+34");
        tef2.setEmpleado(dueño);

        List<AFMNumeroTelefono> telefonos = new ArrayList<AFMNumeroTelefono>();
        telefonos.add(tef1);
        telefonos.add(tef2);
        return telefonos;
    }

    private AFMEmpleado buscaEmpleadoPorId(int i) {
        return em.find(AFMEmpleado.class, i);
    }

    private AFMSeguroSanitario crearSeguroSanitario(String aseguradora) {
        AFMSeguroSanitario seguro = new AFMSeguroSanitario();
        seguro.setAseguradora(aseguradora);
        return seguro;
    }

    private void crearProyectos(Set<AFMEmpleado> empleados) {

        AFMProyecto proy1 = new AFMProyecto("Software gestion sanitaria", empleados);
        AFMEmpleado aux = new AFMEmpleado();
        for (AFMEmpleado e : empleados) {
            e.getProyectos().add(proy1);
            if (e.getNombre().equals("Alejandro")) {
                aux = e;
            }
        }

        Set<AFMEmpleado> empleados2 = new HashSet<AFMEmpleado>();
        empleados2.add(aux);
        AFMProyecto proy2 = new AFMProyecto("Renovacion sistema de informacion", empleados2);
        aux.getProyectos().add(proy2);

        em.persist(proy1);
        em.persist(proy2);
    }

    /**
     * Crea la colección de departamentos, los hace persistentes
     * @return Los departamentos creados
     */
    private Set<AFMDepartamento> crearDepartamentos() {
        Set<AFMDepartamento> departamentos = new HashSet<AFMDepartamento>();

        AFMDepartamento lsi = new AFMDepartamento();
        lsi.setNombre("Lenguajes y Sistemas Informáticos");
        lsi.setDescripcion("");
        lsi.setAbreviatura("LSI");
        lsi.setUrl("www.lsi.us.es");
        departamentos.add(lsi);

        AFMDepartamento ma1 = new AFMDepartamento();
        ma1.setNombre("Matemática Aplicada I");
        ma1.setDescripcion("");
        ma1.setAbreviatura("MA1");
        ma1.setUrl("ma1.eii.us.es");
        departamentos.add(ma1);

        AFMDepartamento ccia = new AFMDepartamento();
        ccia.setNombre("Ciencias de la Computación e Inteligencia Artificial");
        ccia.setDescripcion("");
        ccia.setAbreviatura("CCIA");
        ccia.setUrl("www.cs.us.es");
        departamentos.add(ccia);

        departamentos.forEach((departamento) -> em.persist(departamento));

        return departamentos;
    }
}
