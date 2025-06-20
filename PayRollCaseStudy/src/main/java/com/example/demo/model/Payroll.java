package com.example.demo.model;
import java.sql.Date;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Payroll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int payrollId;

    private Date payrollDate;

    private double amount;

    @ManyToOne
    @JoinColumn(name = "emp_id")
    private employeeProfile employee;
}