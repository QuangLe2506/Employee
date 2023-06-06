package vn.com.devmaster.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.com.devmaster.service.exception.ResourceNotFoundException;
import vn.com.devmaster.service.model.Employee;
import vn.com.devmaster.service.repository.EmployeeRespository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

    @Autowired
    private EmployeeRespository employeeRespository;

    //get all employee
    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return employeeRespository.findAll();
    }

    //create employee rest API
    @PostMapping("/employees")
    public Employee createEmployee( @RequestBody Employee employee){
        return employeeRespository.save(employee);
    }

    //get employee by id rest api
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
        Employee employee = employeeRespository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));
        return ResponseEntity.ok(employee);
    }

    // update employee rest api
    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,@RequestBody Employee employeeDetails){
        Employee employee = employeeRespository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));
        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setEmailId(employeeDetails.getEmailId());

        Employee updateEmployee = employeeRespository.save(employee);
        return ResponseEntity.ok(updateEmployee);
    }

    //delete employee rest api
    @DeleteMapping("/employees/{id}")
    public ResponseEntity< Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
        Employee employee = employeeRespository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));
        employeeRespository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
