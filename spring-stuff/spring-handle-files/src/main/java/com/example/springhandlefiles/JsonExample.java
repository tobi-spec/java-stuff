package com.example.springhandlefiles;

import java.util.List;

public class JsonExample {
    String name;
    int age;
    List<String> jobs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<String> getJobs() {
        return jobs;
    }

    public void setJobs(List<String> jobs) {
        this.jobs = jobs;
    }

    @Override
    public String toString() {
        return "JsonExample{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", jobs=" + jobs +
                '}';
    }
}
