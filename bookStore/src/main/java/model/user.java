/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author david
 */
@Entity
public class user implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToMany(mappedBy = "user")
    private List<loan> loan;

    public user() {
    }

    public user(int id, String name, List<loan> loan) {
        this.id = id;
        this.name = name;
        this.loan = loan;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<loan> getLoan() {
        return loan;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLoan(List<loan> loan) {
        this.loan = loan;
    }
      
    
}
