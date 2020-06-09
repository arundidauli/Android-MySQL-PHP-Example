package com.techtutz.phpexample.model;

public class Patient {


    private String patient_id;
    private String patient_name;
    private String patient_contact;

    public Patient(String patient_id, String patient_name, String patient_contact) {
        this.patient_id = patient_id;
        this.patient_name = patient_name;
        this.patient_contact = patient_contact;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public String getPatient_contact() {
        return patient_contact;
    }

    public void setPatient_contact(String patient_contact) {
        this.patient_contact = patient_contact;
    }
}
