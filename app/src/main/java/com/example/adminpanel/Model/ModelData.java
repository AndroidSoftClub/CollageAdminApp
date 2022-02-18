package com.example.adminpanel.Model;

import java.util.ArrayList;

public class ModelData {
    public String functionName;
    public ArrayList<String> studentList = new ArrayList<>();
    public ArrayList<String> imageList = new ArrayList<>();
    public String summery;

    public ModelData() {
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public ArrayList<String> getStudentList() {
        return studentList;
    }

    public void setStudentList(ArrayList<String> studentList) {
        this.studentList = studentList;
    }

    public ArrayList<String> getImageList() {
        return imageList;
    }

    public void setImageList(ArrayList<String> imageList) {
        this.imageList = imageList;
    }

    public String getSummery() {
        return summery;
    }

    public void setSummery(String summery) {
        this.summery = summery;
    }

    public ModelData(String functionName, ArrayList<String> studentList, ArrayList<String> imageList, String summery) {
        this.functionName = functionName;
        this.studentList = studentList;
        this.imageList = imageList;
        this.summery = summery;
    }
}
