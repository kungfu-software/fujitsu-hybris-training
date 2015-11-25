package au.com.fujitsu.trainingstorefront.forms;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by seabookchen on 15/06/15.
 */
public class CustomerForm {
    @NotEmpty(message = "Please enter your Name.")
    String name;

    @NotEmpty(message = "Please enter your email addresss.")
    @Email
    String email;

    @Min(0)
    int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
