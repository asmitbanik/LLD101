import java.util.*;

public class DeviceRegistry {
    private final List<Object> devices = new ArrayList<>();

    public void add(Object d) { devices.add(d); }

    public <T> T getFirstOfType(Class<T> cls) {
        for (Object d : devices) {
            if (cls.isInstance(d)) {
                return cls.cast(d);
            }
        }
        throw new IllegalStateException("Missing: " + cls.getSimpleName());
    }
}
