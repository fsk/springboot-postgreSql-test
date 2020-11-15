package com.frknshnklksz.springboot.service;

import com.frknshnklksz.springboot.exception.EmployeeExceptions;
import com.frknshnklksz.springboot.models.Employee;
import com.frknshnklksz.springboot.repository.EmployeeRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;


    public List<Employee> getAllUsers() {
        try {
            return employeeRepository.findAll();
        }catch (Exception e){
            throw  new  IllegalArgumentException(EmployeeExceptions.GET_ALL_EMPLOYEE_EXCEPTION);
        }

    }

    public ResponseEntity<Employee> getEmployeeById(Long id) throws Exception {
        try{
            Employee employee = employeeRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException(EmployeeExceptions.EMPLOYEE_NOT_FOUND));
            return ResponseEntity.ok().body(employee);
        } catch (NotFoundException e) {
            throw new Exception(EmployeeExceptions.EMPLOYEE_GET_BY_ID_EXCEPTION);
        }
    }

    public Employee insertEmplooye(Employee employee) throws Exception {
        try {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            LocalDateTime date = LocalDateTime.now();

            Employee employeeByEmail = employeeRepository.findEmployeeByEmail(employee.getEmail());
            Employee employeeByUserName = employeeRepository.findEmployeeByUserName(employee.getUserName());

            if (employee.getName() == null || employee.getName().equals("")
            && employee.getSurname() == null || employee.getSurname().equals("")
            && employee.getUserName() == null || employee.getUserName().equals("")
            && employee.getEmail() == null || employee.getEmail().equals("")){
                throw new IllegalArgumentException(EmployeeExceptions.COMPULSORY_FIELDS + EmployeeExceptions.COMPULSROY_FIELDS);
            }
            // strategy pattern


            if (employeeByEmail != null){
                throw new IllegalArgumentException(EmployeeExceptions.EMAIL_ALREADY_EXISTS);
            }else if(!employee.getEmail().contains("@")){
                throw new IllegalArgumentException(EmployeeExceptions.INVALID_EMAIL);
            }

            if (employeeByUserName != null){
                throw new IllegalArgumentException(EmployeeExceptions.USER_NAME_ALREADY_EXISTS);
            }else if(employee.getUserName().trim().length() < 3){
                throw new IllegalArgumentException(EmployeeExceptions.MIN_CHARACTER_EXCEPTION);
            }

            if (employee.getIdentityNumber().trim().length() != 11){
                throw new SQLException(EmployeeExceptions.IDENTITY_NUMBER_EXCEPTION);
            }

            if (employee.getGender().trim().length() != 1){
                throw new SQLException(EmployeeExceptions.GENDER_EXCEPTION);
            }

            employee.setDateOfCreate(date.format(formatter));

            return employeeRepository.save(employee);

        }catch (Exception e){
            throw new Exception(EmployeeExceptions.INSERT_EXCEPTION);
        }
    }

    public ResponseEntity<Employee> updateEmployeeById(Long id, Employee employeeDetails) throws Exception {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime date = LocalDateTime.now();

        try {
            Employee employee = employeeRepository.findById(id).orElseThrow(
                    () -> new NotFoundException(EmployeeExceptions.EMPLOYEE_NOT_FOUND)
            );
            if (employeeDetails.getName() != null){
                employee.setName(employeeDetails.getName());
            }

            if(employeeDetails.getSurname() != null){
                employee.setSurname(employeeDetails.getSurname());
            }

            if(employeeDetails.getAge() != null){
                employee.setAge(employeeDetails.getAge());
            }

            if (employeeDetails.getUserName() != null){
                employee.setUserName(employeeDetails.getUserName());
            }
            
            if(employeeDetails.getBirthday() != null){
                employee.setBirthday(employeeDetails.getBirthday());
            }
            
            employee.setDateOfUpdate(date.format(formatter));

            if(employeeDetails.getIdentityNumber() != null){
                throw new Exception(EmployeeExceptions.UPDATE_NOT_IDEDITY_NUMBER);
            }

            if(employeeDetails.getId() != null){
                throw new Exception(EmployeeExceptions.UPDATE_NOT_ID);
            }

            if(employeeDetails.getDateOfCreate() != null){
                throw new Exception(EmployeeExceptions.UPDATE_NOT_DATE_OF_CREATE);
            }

            if(employeeDetails.getGender() != null){
                throw new Exception(EmployeeExceptions.UPDATE_NOT_GENDER);
            }

            return ResponseEntity.ok(employeeRepository.save(employee));

        }catch (Exception e){
            throw new Exception(EmployeeExceptions.UPDATE_BY_ID_EXCEPTION);
        }
    }

    public Map<String, Boolean> deleteEmployeeById(Long id) throws Exception {
        try {
            Employee employee = employeeRepository.findById(id).orElseThrow(
                    () -> new NotFoundException(EmployeeExceptions.EMPLOYEE_NOT_FOUND)
            );

            employeeRepository.delete(employee);
            Map<String, Boolean> response = new HashMap<>();
            response.put("DELETED", Boolean.TRUE);
            return response;

        } catch (NotFoundException e) {
            throw new Exception(EmployeeExceptions.DELETE_BY_ID_EXCEPTION);
        }
    }

    public void deleteAllEmployees() throws Exception {
        try{
            employeeRepository.deleteAll();
        }catch (Exception e){
            throw new Exception(EmployeeExceptions.DELETE_ALL_EMPLOYEES_EXCEPTION);
        }
    }
}
