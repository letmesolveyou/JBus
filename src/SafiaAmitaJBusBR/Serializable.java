package SafiaAmitaJBusBR;
import java.util.HashMap;

public class Serializable implements Comparable<Serializable> {
    public final int id;
    private static HashMap<Class<?>, Integer> mapCounter = new HashMap<>();

    protected Serializable() {
        Class<?> clazz = this.getClass();
        if (!mapCounter.containsKey(clazz)) {
            mapCounter.put(clazz, 1);
        }
        int serial = mapCounter.get(clazz);
        this.id = serial;
        mapCounter.put(clazz, serial + 1);
    }

    public static int setLastAssignedId(Class<?> clazz, int value) {
        return mapCounter.put(clazz, value);

    }

    public static int getLastAssignedId(Class<?> clazz) {
        return mapCounter.getOrDefault(clazz, 0);
    }

    @Override
    public int compareTo(Serializable other) {
        return Integer.compare(this.id, other.id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            Serializable that = (Serializable) obj;
            return this.id == that.id;
        }
        return false;
    }

    public boolean equals(Serializable other) {
        return this.id == other.id;
    }
}
