/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kz.bee.doni.angulartest.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.StringWriter;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import kz.bee.doni.angulartest.Employee;
import kz.bee.doni.impl.EmployeeLocal;

/**
 *
 * @author Doni
 */
@Path("employee")
public class EmployeeFacadeREST {
    
    private EmployeeLocal sessionBean;

    public EmployeeFacadeREST() {}

    @GET
    @Produces("application/jsonp")
    public String findAll() {
        System.out.println("I AM IN DA FINDALL");
        List<Employee> emps = null;
        Context ctx = null;
        
        StringWriter stringEmp = null;
        try {
            ctx = new InitialContext();
            if(ctx == null){
                System.out.println("CONTEX IS NULL");
            } else {
                System.out.println("CONTEX IS NOT NULL");
            }
            sessionBean = (EmployeeLocal) ctx.lookup("java:global/AngularTest-1.0-SNAPSHOT/EmployeeImpl!kz.bee.doni.impl.EmployeeLocal");
            if(sessionBean == null){
                System.out.println("SESSIONBEAN IS NULL");
            } else {
                System.out.println("SESSIONBEAN IS NOT NULL");
            }
            emps = sessionBean.findAll();
            
            ObjectMapper objectMapper = new ObjectMapper();

            stringEmp = new StringWriter();
            
            System.out.println("JSON: "+stringEmp.toString());
            
            //List<Employee> users = userService.getAll();
            objectMapper.writeValue(stringEmp, emps);
            
            if(emps != null){
                System.out.println("EMPS NOT NULL");
            } else {
                System.out.println("EMPS - NULL");
            }
        } catch(Exception e){
            System.out.println("Exception log: "+e.getMessage());
        }
        return stringEmp.toString();
    }

    /*@POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Employee entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    @Override
    public void edit(@PathParam("id") Long id, Employee entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    @Override
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    @Override
    public Employee find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    @Override
    public List<Employee> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    @Override
    public String countREST() {
        return String.valueOf(super.count());
    }*/
    
}
