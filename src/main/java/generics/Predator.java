package generics;

import java.util.Objects;

public class Predator extends Animal {
    private String typeHunting;

    public Predator() {
    }

    public String getTypeHunting() {
        return typeHunting;
    }

    @Override
    public String toString() {
        return "Predator{"
                + "Стиль охоты='" + typeHunting + '\''
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Predator)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Predator predator = (Predator) o;
        return Objects.equals(typeHunting, predator.typeHunting);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), typeHunting);
    }
}
