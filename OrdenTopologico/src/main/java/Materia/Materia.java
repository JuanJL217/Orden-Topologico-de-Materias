package Materia;

import java.util.HashSet;
import java.util.Set;

public class Materia {
    private Integer code;
    private String name;
    private Set<Integer> dependenciesCode = new HashSet<>();

    public Materia(Integer _code, String _name) {
        this.code = _code;
        this.name = _name;
    }

    public String getName() {
        return this.name;
    }

    public Integer getCode() {
        return this.code;
    }

    public boolean addDependenciesCode(Integer _code) {
        return this.dependenciesCode.add(_code);
    }

    public Set<Integer> getDependenciesCode() {
        return new HashSet<>(this.dependenciesCode);
    }
}
