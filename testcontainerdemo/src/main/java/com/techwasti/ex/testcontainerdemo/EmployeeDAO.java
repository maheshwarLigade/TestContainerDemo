package com.techwasti.ex.testcontainerdemo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmployeeDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;
    
    public List<Employee> getEmployees() {
        return jdbcTemplate.query(
            "SELECT emp_id, first_name, last_name FROM EMPLOYEE",
            (rs, rowNum) -> new Employee(rs.getLong("emp_id"), rs.getString("first_name"), rs.getString("last_name"))
    );
    }
    
}
