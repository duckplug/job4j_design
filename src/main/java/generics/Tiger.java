package generics;

import java.util.Objects;

public class Tiger extends Predator {
    private String species;

    public Tiger() {
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    @Override
    public String toString() {
        return "Tiger{" +
                "Вид ='" + species + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tiger)) return false;
        Tiger tiger = (Tiger) o;
        return Objects.equals(species, tiger.species);
    }

    @Override
    public int hashCode() {
        return Objects.hash(species);
    }
}
