package com.example;


import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;

public class MyFirstDaoGenerator {

    public static void main(String args[]) throws Exception {
        Schema schema = new Schema(1, "com.example.myfirstdaogeneraotor.model");



        Entity employee = schema.addEntity("Employee");
        employee.addIdProperty();
        employee.addStringProperty("name");
        employee.addStringProperty("description");
        Entity admin = schema.addEntity("Admin");


        admin.addIdProperty();
        admin.addStringProperty("name");
        admin.addStringProperty("company");
        admin.addLongProperty("rollno");
        admin.addLongProperty("employeeno");

        Property employeeId = admin.addLongProperty("employeeId").getProperty();
        admin.addToOne(employee, employeeId);
        new DaoGenerator().generateAll(schema, "../app/src/main/java");

    }
}
