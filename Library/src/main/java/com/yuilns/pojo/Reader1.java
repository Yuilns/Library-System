package com.yuilns.pojo;

public class Reader1 {
    private Integer Reader_id;
    private String readername;
    private Integer age;
    private String gender;
    private String tel;
    private String idcardnumber;

    public Integer getReader_id() {
        return Reader_id;
    }

    public void setReader_id(Integer reader_id) {
        Reader_id = reader_id;
    }

    public String getReader_name() {
        return readername;
    }

    public void setReader_name(String reader_name) {
        readername = reader_name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getIdcard_number() {
        return idcardnumber;
    }

    public void setIdcard_number(String idcard_number) {
        this.idcardnumber = idcard_number;
    }

    @Override
    public String toString() {
        return "Reader{" +
                "Reader_id=" + Reader_id +
                ", Reader_name='" + readername + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", tel='" + tel + '\'' +
                ", idcard_number='" + idcardnumber + '\'' +
                '}';
    }
}
