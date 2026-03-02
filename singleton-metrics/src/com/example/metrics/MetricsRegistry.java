package com.example.metrics;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * INTENTION: Global metrics registry (should be a Singleton).
 *
 * CURRENT STATE (BROKEN ON PURPOSE):
 * - Constructor is public -> anyone can create instances.
 * - getInstance() is lazy but NOT thread-safe -> can create multiple instances.
 * - Reflection can call the constructor to create more instances.
 * - Serialization can create a new instance when deserialized.
 *
 * Implementation is now a thread-safe lazy singleton with reflection/serialization
 * safeguards.
 */
public class MetricsRegistry implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    // flag used to block reflection-based instantiation
    private static volatile boolean created = false;
    private final Map<String, Long> counters = new HashMap<>();

    // private constructor; throws if called more than once (reflection guard)
    private MetricsRegistry() {
        if (created) {
            throw new IllegalStateException("MetricsRegistry instance already created");
        }
        created = true;
    }

    // lazy-loaded holder idiom ensures thread-safe lazy initialization
    private static class Holder {
        private static final MetricsRegistry INSTANCE = new MetricsRegistry();
    }

    public static MetricsRegistry getInstance() {
        return Holder.INSTANCE;
    }

    public synchronized void setCount(String key, long value) {
        counters.put(key, value);
    }

    public synchronized void increment(String key) {
        counters.put(key, getCount(key) + 1);
    }

    public synchronized long getCount(String key) {
        return counters.getOrDefault(key, 0L);
    }

    public synchronized Map<String, Long> getAll() {
        return Collections.unmodifiableMap(new HashMap<>(counters));
    }

    // preserve singleton during deserialization
    private Object readResolve() {
        return getInstance();
    }
}
