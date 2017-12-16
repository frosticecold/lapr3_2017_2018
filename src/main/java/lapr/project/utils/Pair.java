/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import java.util.Objects;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public class Pair<V, E> {

    private V v;
    private E e;

    public Pair(V firstElement, E secondElement) {
        v = firstElement;
        e = secondElement;
    }

    public V getFirstElement() {
        return v;
    }

    public E getSecondElement() {
        return e;
    }

    public void setFirstElement(V v) {
        this.v = v;
    }

    public void setSecondElement(E e) {
        this.e = e;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.v);
        hash = 29 * hash + Objects.hashCode(this.e);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        @SuppressWarnings("unchecked")
        Pair<V,E> p = (Pair<V,E>) obj;

        return this.getFirstElement().equals(p.getFirstElement()) && this.getSecondElement().equals(p.getSecondElement());
    }

}
