package br.edu.ufal.kcaj.iFace.utils;

public class Pair<K, V> {
    public final K first;
    public final V second;

    public Pair(K first, V second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || this.getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return this.first.equals(pair.first) && this.second.equals(pair.second);
    }
    @Override
    public String toString() {
        return "(" + this.first + ", " + this.second + ")";
    }


}
