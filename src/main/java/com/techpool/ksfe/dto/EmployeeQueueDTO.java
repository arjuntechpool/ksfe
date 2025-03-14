package com.techpool.ksfe.dto;

public class EmployeeQueueDTO {
    private String employeeCode;
    private String employeeName;
    private Integer preferredOffice;
    private Integer preferenceOrder;
    private Integer priorityValue;

    // Constructor
    public EmployeeQueueDTO(String employeeCode, String employeeName, Integer preferredOffice, Integer preferenceOrder, Integer priorityValue) {
        this.employeeCode = employeeCode;
        this.employeeName = employeeName;
        this.preferredOffice = preferredOffice;
        this.preferenceOrder = preferenceOrder;
        this.priorityValue = priorityValue;
    }

    // Getters and Setters
    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Integer getPreferredOffice() {
        return preferredOffice;
    }

    public void setPreferredOffice(Integer preferredOffice) {
        this.preferredOffice = preferredOffice;
    }

    public Integer getPreferenceOrder() {
        return preferenceOrder;
    }

    public void setPreferenceOrder(Integer preferenceOrder) {
        this.preferenceOrder = preferenceOrder;
    }

    public Integer getPriorityValue() {
        return priorityValue;
    }

    public void setPriorityValue(Integer priorityValue) {
        this.priorityValue = priorityValue;
    }
}