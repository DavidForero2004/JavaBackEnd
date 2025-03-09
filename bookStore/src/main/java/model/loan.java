/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author david
 */
@Entity
public class loan implements Serializable {

     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String dateLoan;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private user user;

    @ManyToOne
    @JoinColumn(name = "exemplars_id")
    private exemplars exemplars;

    public loan() {
    }

    public loan(int id, String dateLoan, user user, exemplars exemplars) {
        this.id = id;
        this.dateLoan = dateLoan;
        this.user = user;
        this.exemplars = exemplars;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateLoan() {
        return dateLoan;
    }

    public void setDateLoan(String dateLoan) {
        this.dateLoan = dateLoan;
    }

    public user getUser() {
        return user;
    }

    public void setUser(user user) {
        this.user = user;
    }

    public exemplars getExemplars() {
        return exemplars;
    }

    public void setExemplars(exemplars exemplars) {
        this.exemplars = exemplars;
    }
    
    
}
