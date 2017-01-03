/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.domain.cafeteria;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import eapli.framework.domain.AggregateRoot;

/**
 *
 * @author arocha
 */
@Entity
public class OrganicUnit implements AggregateRoot<String>, Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String acronym;
    private String name;
    private String description;
    private boolean active;

    protected OrganicUnit() {
        // for ORM
    }

    public OrganicUnit(String acronym, String name, String description) {
        if (acronym == null || name == null || description == null || acronym.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }
        // TODO name and description provably should not be empty
        this.acronym = acronym;
        this.name = name;
        this.description = description;
        this.active = true;
    }

    @Override
    public String id() {
        return this.acronym;
    }

    @Override
    public boolean is(String id) {
        return id.equalsIgnoreCase(this.acronym);
    }

    public boolean isActive() {
        return this.active;
    }

    public String name() {
        return this.name;
    }

    public String description() {
        return this.description;
    }

    // FIXME implement equals() and hashCode()

    @Override
    public boolean sameAs(Object other) {
        // TODO Auto-generated method stub
        return false;
    }
}
