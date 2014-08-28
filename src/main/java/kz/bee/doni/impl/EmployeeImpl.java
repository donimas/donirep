package kz.bee.doni.impl;

import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import kz.bee.doni.angulartest.Employee;

@Stateless(name = "EmployeeImpl", mappedName = "EmployeeImpl")
public class EmployeeImpl implements EmployeeLocal {
    
    @PersistenceContext(unitName = "kz.bee.doni_AngularTest_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    
    @Resource
    private UserTransaction ut;
    
    @Override
    public List<Employee> findAll() {
        System.out.println("I AM IN DA FIND_ALL METHOD");
        List<Employee> emps = null;
        if(ut!=null){
            System.out.println("UserTransaction is not null");
        } else {
            System.out.println("UserTransaction is null");
        }
        try{
            if(em != null){
                System.out.println("EntityManager is not null");
                emps = em.createQuery("Select e From Employee e").getResultList();
                System.out.println("FIND_ALL: "+emps.toString());
            } else {
                System.out.println("EntityManager is null");
            }
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        return emps;
    }
    
}
