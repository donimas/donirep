package kz.bee.doni.angulartest.service;

import java.util.List;
import javax.ejb.Local;
import kz.bee.doni.angulartest.Employee;

@Local
public interface EmployeeFacadeLocal {

    public List<Employee> findAll();
    
    /*public void create(Employee entity);

    public void edit(Long id, Employee entity);

    public void remove(Long id);

    public Employee find(Long id);
    
    public List<Employee> findRange(Integer from, Integer to);

    public String countREST();*/
    
}
