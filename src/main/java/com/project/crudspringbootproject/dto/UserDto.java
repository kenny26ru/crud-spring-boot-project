package com.project.crudspringbootproject.dto;

public class UserDto {
    private Long id;

    private String email;

    private String name;

    private String secondName;

    private int age;

    private String password;

    private String roleSet;

    public UserDto(Long id, String email, String name, String secondName, int age, String password, String roleSet) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.secondName = secondName;
        this.age = age;
        this.password = password;
        this.roleSet = roleSet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(String roleSet) {
        this.roleSet = roleSet;
    }
}
