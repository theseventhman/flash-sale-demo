package com.tj.exercise.flash.sale.demo.util;

/**
 * @Author: tj
 * @Date: 2023/6/24 22:00
 */
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class NullToEmptyStringsReflectionExample {

    public static void main(String[] args) {
        // 创建一个包含对象的集合
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("John", null, "123 Main St"));
        personList.add(new Person(null, "Doe", null));
        personList.add(new Person("Jane", "Smith", null));

        // 将集合中属性值为 null 的设置为空字符串
        for (Person person : personList) {
            setNullFieldsToEmptyStrings(person);
        }

        // 打印处理后的集合
        for (Person person : personList) {
            System.out.println(person);
        }
    }

    static void setNullFieldsToEmptyStrings(Object obj) {
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(obj);
                if (value == null) {
                    field.set(obj, "");
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    static class Person {
        private String firstName;
        private String lastName;
        private String address;

        public Person(String firstName, String lastName, String address) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.address = address;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", address='" + address + '\'' +
                    '}';
        }
    }
}

