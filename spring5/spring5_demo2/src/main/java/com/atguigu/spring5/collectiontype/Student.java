package com.atguigu.spring5.collectiontype;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by shucheng on 2021/5/2 20:34
 */
public class Student {
    // 1 数组类型属性
    private String[] courses;
    // 2 list集合类型属性
    private List<String> list;
    // 3 map集合类型属性
    private Map<String, String> map;
    // 4 set集合类型属性
    private Set<String> set;

    // 学生所学多门课程
    private List<Course> courseList;

    public void setCourses(String[] courses) {
        this.courses = courses;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public void setSet(Set<String> set) {
        this.set = set;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    @Override
    public String toString() {
        return "Student{" +
                "courses=" + Arrays.toString(courses) +
                ", list=" + list +
                ", map=" + map +
                ", set=" + set +
                ", courseList=" + courseList +
                '}';
    }
}
