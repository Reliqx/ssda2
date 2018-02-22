/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ssd.ssda2.business;

import javax.validation.constraints.*;
import org.hibernate.validator.constraints.*;

/**
 *
 * @author Alvin Pascua
 */
public class User {
    
    @NotBlank(message = "Missing username")
    @Pattern(regexp = "(([a-zA-Z]+['.,-]?)|\\s)*", message = "Invalid username")
    private String username = "";

    @NotBlank(message = "Missing password")
    private String password = "";
    
    @NotBlank(message = "Missing email")
    private String email = "";

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    } 

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
