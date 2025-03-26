/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import jakarta.persistence.OneToMany;
import java.io.Serializable;
import java.util.LinkedList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author juand
 */
@Entity
@Table(name = "users")
public class user implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String email;
    private String password;
    
    @OneToMany
    private LinkedList<Rol> id_rol;

    public user() {
    }

    public user(int id, String email, String password, LinkedList<Rol> id_rol) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.id_rol = id_rol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LinkedList<Rol> getId_rol() {
        return id_rol;
    }

    public void setId_rol(LinkedList<Rol> id_rol) {
        this.id_rol = id_rol;
    }

    

    @Override
    public String toString() {
        return "model.user[ id=" + id + " ]";
    }

}
