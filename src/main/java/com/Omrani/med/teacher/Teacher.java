package com.Omrani.med.teacher;

import com.Omrani.med.user.Role;
import com.Omrani.med.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "teachers")
public class Teacher extends User {
    private String subjectSpecialization;
    private String contactInformation;

    public Teacher() {
        super();
    }

    public Teacher(String userName, Role role, int age, String email, String subjectSpecialization, String contactInformation) {
        super(userName, role, age, email);
        this.subjectSpecialization = subjectSpecialization;
        this.contactInformation = contactInformation;
    }


    public String getSubjectSpecialization() {
        return subjectSpecialization;
    }

    public void setSubjectSpecialization(String subjectSpecialization) {
        this.subjectSpecialization = subjectSpecialization;
    }

    public String getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }
}
