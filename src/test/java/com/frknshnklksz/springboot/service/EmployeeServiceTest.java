package com.frknshnklksz.springboot.service;

import com.frknshnklksz.springboot.controller.EmployeeController;
import com.frknshnklksz.springboot.models.Employee;
import com.frknshnklksz.springboot.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;


//@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class EmployeeServiceTest {

    //@InjectMocks
    //private EmployeeController employeeController;


    //@InjectMocks
    //public EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;

    //@InjectMocks
    //private EmployeeRepository employeeRepository;


    @Test
    public void testInsert() throws Exception {
        Employee employee = new Employee();
        employee.setName("Test-Ahmet");
        employee.setSurname("Test-Kulaksiz");
        employee.setUserName("Ahmet-Kulaksiz");
        employee.setAge(26);
        employee.setEmail("ahmetkulaksiz@gmail.com");
        employee.setIdentityNumber("12345678912");
        //employee.setBirthday(LocalDate.of(1994,11,21));
        employee.setGender("E");
        
        
        
        
        
        //System.out.println(employee.getIdentityNumber());

        Employee employeeMock = mock(Employee.class);

        when(employeeMock.getId()).thenReturn(1L);
        when(employeeRepository.save(ArgumentMatchers.any(Employee.class))).thenReturn(employeeMock);
        Employee resultEmployee = employeeRepository.save(employee);

        //ResponseEntity<Employee> employeeById = employeeService.getEmployeeById(1L);

//        if (employeeById != null){
//            
//        }


        assertEquals(resultEmployee.getName(), employeeMock.getName());
        assertEquals(resultEmployee.getId(), 1L);
            //assertThrows()
    }

}
