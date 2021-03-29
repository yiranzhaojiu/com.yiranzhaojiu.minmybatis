package com.yiranzhaojiu.minmybatis.v1.entity;

public class EmpUserEntity {
    private Integer emp_id;
    private String emp_name;
    private String gender;
    private String email;
    private Integer d_id;

    public Integer getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(Integer emp_id) {
        this.emp_id = emp_id;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getD_id() {
        return d_id;
    }

    public void setD_id(Integer d_id) {
        this.d_id = d_id;
    }

    @Override
    public String toString() {
        return "EmpUserEntity{" +
                "emp_id=" + emp_id +
                ", emp_name='" + emp_name + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", d_id=" + d_id +
                '}';
    }
}
