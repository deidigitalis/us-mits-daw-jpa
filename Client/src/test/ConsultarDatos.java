package test;

//import entity.AFMDepartamento;
import entity.AFMEmpleado;

//import entity.AFMProyecto;

import entity.AFMProyecto;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

public class ConsultarDatos {
    
    @PersistenceUnit
    EntityManagerFactory emf;
    
    EntityManager em;
    public ConsultarDatos() {
        super();
        emf = Persistence.createEntityManagerFactory("EmpleadoHibernateConsultas");
        em = emf.createEntityManager();
    }

    public static void main(String[] args) {
        ConsultarDatos consultarDatos = new ConsultarDatos();
   //     consultarDatos.consultaBorrado();
        consultarDatos.consultaEstatica();
        consultarDatos.consultaNativa();
        
        consultarDatos.consultaDinamica();
        consultarDatos.consultaUpdate();
        
        consultarDatos.consultaDinamica();
        
        List<AFMEmpleado> empleados = consultarDatos.findAllEmpleados();
        System.out.println("--Empleados--");
        for (AFMEmpleado e: empleados){
          //  System.out.println("Telefonos: "+e.getNumerosDeTelefono());
            System.out.println(e);
        }
        
        System.out.println("--Proyectos--");
        List<AFMProyecto> proyectos = consultarDatos.findAllProyectos();
        for(AFMProyecto p: proyectos){
            System.out.println(p);
        }
//        
//        AFMDepartamento d = consultarDatos.buscaDepartamento();
//        System.out.println("Departamento: "+d);
//        
    }

    private List<AFMEmpleado> findAllEmpleados() {
        Query q = em.createNamedQuery("AFMEmpleado.findAll");
        return q.getResultList();
    }
//
//    private AFMDepartamento buscaDepartamento() {
//        AFMDepartamento d = em.find(AFMDepartamento.class, 1);
//        return d;
//    }
//
    private List<AFMProyecto> findAllProyectos() {
        Query q = em.createNamedQuery("AFMProyecto.findAll");
        return q.getResultList();
    }

    private void consultaDinamica() {

        Query q = em.createQuery("SELECT e FROM AFMEmpleado e WHERE e.nombre LIKE :nombre");
        q.setParameter("nombre", "Alejandro");
        q.setMaxResults(10);
        List<AFMEmpleado> resultados = q.getResultList();
        
        System.out.println("--- Consulta dinámica. Empleados de nombre Alejandro ---");
        for (AFMEmpleado e: resultados){
          //  System.out.println("Telefonos: "+e.getNumerosDeTelefono());
            em.refresh(e);
            System.out.println(e);
        }
    }
    
    private void consultaEstatica() {

        Query q = em.createNamedQuery("AFMEmpleado.findAllOrderBySalary");
        
        q.setMaxResults(10);
        List<AFMEmpleado> resultados = q.getResultList();
        
        System.out.println("--- Consulta estatica. Empleados ordenados por saliario ---");
        for (AFMEmpleado e: resultados){
          //  System.out.println("Telefonos: "+e.getNumerosDeTelefono());
            em.refresh(e);
            System.out.println(e);
        }
    }
    
    private void consultaUpdate(){
        
        Query q = em.createQuery("UPDATE AFMEmpleado e SET e.salario = e.salario*1.2 WHERE e.nombre LIKE :nombre");
        q.setParameter("nombre", "Alejandro");
        em.getTransaction().begin();
        q.executeUpdate();
        em.getTransaction().commit();
    }

    private void consultaNativa(){
        Query q = em.createNativeQuery
               ("SELECT * FROM AFMProyecto ORDER BY ID DESC", AFMProyecto.class);
        List<AFMProyecto> proyectos = q.getResultList();
        System.out.println("--- Consulta nativa de proyectos ---");
        for(AFMProyecto p: proyectos){
            System.out.println(p);
        }
    }
//    
    private void consultaBorrado(){
        
        Query q = em.createQuery("DELETE FROM AFMSeguroSanitario");
        em.getTransaction().begin();
        q.executeUpdate();
        em.getTransaction().commit();
    }
    
}
