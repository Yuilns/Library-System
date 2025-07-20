package com.yuilns.pojo;

public class Manager {
    private Integer User_id;
    private String manager_name;
    private String area;
    private Integer age;
    private String gender;
    private String tel;
    private String username;
    private String password;
    private Integer root;

    public Integer getUser_id() {
        return User_id;
    }

    public void setUser_id(Integer user_id) {
        User_id = user_id;
    }

    public String getManager_name() {
        return manager_name;
    }

    public void setManager_name(String manager_name) {
        this.manager_name = manager_name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRoot() {
        return root;
    }

    public void setRoot(Integer root) {
        this.root = root;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "User_id=" + User_id +
                ", manager_name='" + manager_name + '\'' +
                ", area='" + area + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", tel='" + tel + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", root=" + root +
                '}';
    }
}
