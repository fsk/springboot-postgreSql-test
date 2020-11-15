package com.frknshnklksz.springboot.controller;

import com.frknshnklksz.springboot.models.Employee;
import com.frknshnklksz.springboot.service.EmployeeService;
import com.frknshnklksz.springboot.url.EmployeeUrl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = EmployeeUrl.BASE_URL)
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;


    @GetMapping(value = EmployeeUrl.GET_ALL_EMPLOYEES)
    public List<Employee> getAllEmployees(){
        return employeeService.getAllUsers();
    }

    @GetMapping(value = EmployeeUrl.GET_EMPLOYEE_BY_ID + "{id}")
    public ResponseEntity<Employee> getEmplooyeById(@PathVariable(value = "id") Long id) throws Exception {
        return employeeService.getEmployeeById(id);
    }

    @PostMapping(value = EmployeeUrl.INSRET_EMPLOOYE)
    public Employee insertEmployee(@RequestBody Employee employee) throws Exception {
        return employeeService.insertEmplooye(employee);
    }

    @PutMapping(value = EmployeeUrl.UPDATE_EMPLOOYE_BY_ID + "{id}")
    public ResponseEntity<Employee> updateEmplooye(@PathVariable(value = "id") Long id,
                                                   @RequestBody Employee employeeDetails) throws Exception {
        return employeeService.updateEmployeeById(id, employeeDetails);
    }

    @DeleteMapping(value = EmployeeUrl.DELETE_EMPLOYEE_BY_ID + "{id}")
    public Map<String, Boolean> deleteEmployeeById(@PathVariable("id") Long id) throws Exception {
        return employeeService.deleteEmployeeById(id);
    }


    @DeleteMapping(value = EmployeeUrl.DELETE_ALL_EMPLOYEES)
    public void deleteAllEmployees() throws Exception {
        employeeService.deleteAllEmployees();
    }


}
