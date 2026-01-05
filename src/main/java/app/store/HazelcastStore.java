package app.store;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import app.model.Student;

public class HazelcastStore {

    static HazelcastInstance hz;
    static IMap<String, Student> map;

    public static void init() {
        hz = Hazelcast.newHazelcastInstance(); // 👈 embedded
        map = hz.getMap("ogrenciler");

        for (int i = 0; i < 10000; i++) {
            String id = "2025" + String.format("%06d", i);
            Student s = new Student(id, "Ad Soyad " + i, "Bilgisayar");
            map.put(id, s);
        }

        System.out.println("Hazelcast initialized (embedded) with 10.000 records");
    }

    public static Student get(String id) {
        return map.get(id);
    }
}
