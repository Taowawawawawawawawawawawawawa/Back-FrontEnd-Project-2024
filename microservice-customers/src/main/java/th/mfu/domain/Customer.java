package th.mfu.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// -- INSERT INTO customer (id, name, age, phoneNum, pass) VALUES
// -- (1, 'John Doe', 28, '555-1234', 'password123'),
// -- (2, 'Jane Smith', 35, '555-5678', 'securePass'),
// -- (3, 'Alice Johnson', 24, '555-8765', 'alicePass'),
// -- (4, 'Bob Williams', 40, '555-4321', 'bobPassword');


@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private int age;

    private String phoneNum;
    
    private String pass;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

}
