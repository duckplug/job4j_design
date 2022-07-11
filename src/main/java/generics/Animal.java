package generics;

import java.util.Objects;

public class Animal {
    private String habitat;
    private Integer abundance;

    public Animal() {
    }

    public String getHabitat() {
        return habitat;
    }

    public Integer getAbundance() {
        return abundance;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "ареал обитания='" + habitat + '\'' +
                ", популяция=" + abundance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Animal)) return false;
        Animal animal = (Animal) o;
        return Objects.equals(habitat, animal.habitat) && Objects.equals(abundance, animal.abundance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(habitat, abundance);
    }
}
