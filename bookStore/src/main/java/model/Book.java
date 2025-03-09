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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
/**
 *
 * @author david
 * 
 * */
 
@Entity
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "writer_id")
    private writer writer;

    @OneToMany(mappedBy = "book")
    private List<exemplars> exemplars;

    @ManyToMany
    @JoinTable(
        name = "book_category",
        joinColumns = @JoinColumn(name = "book_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<category> categories;

    public Book() {
    }

    public Book(int id, String titulo, writer writer, List<exemplars> exemplars, List<category> categories) {
        this.id = id;
        this.titulo = titulo;
        this.writer = writer;
        this.exemplars = exemplars;
        this.categories = categories;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public writer getWriter() {
        return writer;
    }

    public void setWriter(writer writer) {
        this.writer = writer;
    }

    public List<exemplars> getExemplars() {
        return exemplars;
    }

    public void setExemplars(List<exemplars> exemplars) {
        this.exemplars = exemplars;
    }

    public List<category> getCategories() {
        return categories;
    }

    public void setCategories(List<category> categories) {
        this.categories = categories;
    }
    
    
}
