package com.techwasti.testcontainer.endendexample;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmpController {
    
@Autowired
EmployeeDAO employeeDAO;

@Autowired
private QueueSender queueSender;

private static final Logger logger = LoggerFactory.getLogger(EmpController.class);


@GetMapping("/getEmployees")
private List<Employee> getEmployees(){

    List<Employee> employees=employeeDAO.getEmployees();
    employees.forEach(emp -> logger.info("Found a employee: {}", emp));
    return employees;
}

@GetMapping("/send")
    public String sendMessage(){
        queueSender.send("Demo message");
        return "Done. Sent";
    }

}
