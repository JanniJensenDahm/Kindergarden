package com.example.kindergarden;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
    private String fileName;
    private Scanner scanner;
    private String employeeFile = "src\\main\\java\\com\\example\\kindergarden\\txtFiles\\employees.txt";

    public FileHandler(){}

    public FileHandler(String fileName) throws Exception {
        this.fileName = fileName;
        scanner = new Scanner(new File("./resources/"+fileName));
    }

    public void saveEmployeeToFile() throws Exception {
      ServiceEmployee serviceEmployee = new ServiceEmployee();
        for (Employee employeeSave: serviceEmployee.getEmployees()){
          PrintStream newEmployeeInfo = new PrintStream(new FileOutputStream(employeeFile,true));
          String employeeInfo = String.format("%s; %s; %s; %s; %s; %s; %s; %s\n",
              employeeSave.getFirstName(),
              employeeSave.getLastName(),
              employeeSave.getRoadName(),
              employeeSave.getRoadNumber(),
              employeeSave.getCity(),
              employeeSave.getPostalCode(),
              employeeSave.getPhoneNumber(),
              employeeSave.getEmail());

          newEmployeeInfo.println(employeeInfo);
          System.out.print("\n");
        }
    }

    public String getFileName() {
        return fileName;
    }

    public FileHandler setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }
}