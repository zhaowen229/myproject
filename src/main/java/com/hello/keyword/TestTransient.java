package com.hello.keyword;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 1）一旦变量被transient修饰，变量将不再是对象持久化的一部分，该变量内容在序列化后无法获得访问。
 *
 * 2）transient关键字只能修饰变量，而不能修饰方法和类。注意，本地变量是不能被transient关键字修饰的。变量如果是用户自定义类变量，则该类需要实现Serializable接口。
 *
 * 3）被transient关键字修饰的变量不再能被序列化，一个静态变量不管是否被transient修饰，均不能被序列化。
 */
public class TestTransient {
    public static void main(String[] args){
        User user = new User("chz",28);
        ObjectOutputStream os = null;
        try {
            os = new ObjectOutputStream(new FileOutputStream("d:/transient.txt"));
            os.writeObject(user);
            os.flush();
            os.close();

            user = new User("chz2",28);//反序列化之前修改值,发现name = "chz2"
            ObjectInputStream is = new ObjectInputStream(new FileInputStream("d:/transient.txt"));
            user = (User) is.readObject();
            is.close();

            System.out.println(user.toString());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}


class User implements Serializable {
    private static String name;
    private transient int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

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

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}