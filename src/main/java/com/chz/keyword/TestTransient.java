package com.chz.keyword;

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

            user = new User("chz2",28);//修改对象名称

            ObjectInputStream is = new ObjectInputStream(new FileInputStream("d:/transient.txt"));
            user = (User) is.readObject();//反序列化 读取用户名称 name=chz2
            is.close();
            System.out.println("deserialization:"+user.toString());//反序列化的结果: User{name=chz2, age=0}
//          age 没有被序列化到二进制字节数组中的，而且userName用static修饰符修饰也不会被序列化到二进制字节数组中,
//          它取出的只是JVM内存中的值（序列化之前以对象保存的形式是chz 而反序列化之后取出的是chz2 证明字节数组无该值
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