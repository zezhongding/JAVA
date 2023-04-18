package edu.ustc;

import edu.ustc.domain.Student;

import java.util.ArrayList;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        ArrayList<Student> list = new ArrayList<>();
        list.add(new Student("1001", "张三", 18, "2000-03-14"));
        list.add(new Student("1002", "李四", 14, "2000-03-14"));
        list.add(new Student("1003", "王五", 20, "1998-12-31"));
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("-----------欢迎来到学生管理系统-----------");
            System.out.println("1. 添加学生");
            System.out.println("2. 删除学生");
            System.out.println("3. 修改学生");
            System.out.println("4. 查看学生");
            System.out.println("5. 退出");
            System.out.println("请输入您的选择：");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    addStudentInfos(list);
                    break;
                case 2:
                    deleteStudentById(list);
                    break;
                case 3:
                    updateStudentById(list);
                    break;
                case 4:
                    queryStudentInfos(list);
                    break;
                case 5:

                    System.exit(0);
                    break;
                default:
                    break;

            }
        }
    }

    /**
     * 添加学生信息s
     * @param list
     */
    private static void addStudentInfos(ArrayList<Student> list) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入新的学生学号：");
        String id = null;
        while (true) {
            id = sc.next();
            int index = getIndex(id, list);
            if(index == -1) {
                break;
            } else {
                System.out.println("您输入学号已被占用，请重新滚输入！");
            }
        }
        System.out.println("请输入新的学生姓名：");
        String name = sc.next();
        System.out.println("请输入新的学生年龄：");
        int age = sc.nextInt();
        System.out.println("请输入新的学生生日：");
        String birthday = sc.next();
        Student stu = new Student(id, name, age, birthday);
        list.add(stu);
        System.out.println("添加成功！");
    }

    /**
     * 根据学号修改学生信息
     * @param list
     */
    private static void updateStudentById(ArrayList<Student> list) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要修改的学生学号：");
        String updateId = sc.next();
        int index = getIndex(updateId, list);
        if(index == -1) {
            System.out.println("查无此人，修改失败！");
        } else {
            System.out.println("请输入新的学生姓名：");
            String name = sc.next();
            System.out.println("请输入新的学生年龄：");
            int age = sc.nextInt();
            System.out.println("请输入新的学生生日：");
            String birthday = sc.next();
            Student stu = new Student(updateId, name, age, birthday);
            list.set(index, stu);
            System.out.println("修改成功!");
        }
    }

    /**
     * 根据学号删除学生
     * @param list
     */
    private static void deleteStudentById(ArrayList<Student> list) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要删除的学生学号：");
        String id = sc.next();
        int index = getIndex(id, list);
        if(index == -1) {
            System.out.println("查无此人，删除失败！");
        } else {
            list.remove(index);
            System.out.println("删除成功！");
        }
    }

    /**
     * 根据学号找集合中对应索引
     * @param id
     * @param list
     * @return
     */
    private static int getIndex(String id, ArrayList<Student> list) {
        for (int i = 0; i < list.size(); i++) {
            Student stu = list.get(i);
            if(stu.getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 此方法用于查看学生
     * @param list
     */
    private static void queryStudentInfos(ArrayList<Student> list) {
        if(list.size() == 0) {
            System.out.println("查无信息请添加后重试！");
        } else {
            System.out.println("学号\t\t\t姓名\t年龄\t生日");
            for (int i = 0; i < list.size(); i++) {
                Student stu = list.get(i);
                System.out.println(stu.getId() + "\t" + stu.getName() + "\t" + stu.getAge() + "\t" + stu.getBirthday());
            }
        }
    }

}
