/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kz.bee.doni.impl;

import java.util.List;
import javax.ejb.Local;
import kz.bee.doni.angulartest.Employee;

@Local
public interface EmployeeLocal {
    
    public List<Employee> findAll();
    
}
