package app.store;

import com.mongodb.client.*;
import static com.mongodb.client.model.Filters.eq;

import org.bson.Document;
import app.model.Student;

public class MongoStore {

    static MongoClient client;
    static MongoCollection<Document> collection;

    public static void init() {

        client = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase db = client.getDatabase("nosqllab");
        collection = db.getCollection("ogrenciler");

        if (collection.countDocuments() == 0) {
            for (int i = 0; i < 10000; i++) {
                String id = "2025" + String.format("%06d", i);

                Document doc = new Document()
                        .append("student_no", id)
                        .append("name", "Ad Soyad " + i)
                        .append("department", "Bilgisayar");

                collection.insertOne(doc);
            }
            System.out.println("MongoDB initialized with 10.000 records");
        }
    }

    public static Student get(String id) {

        Document doc = collection.find(eq("student_no", id)).first();

        if (doc == null) return null;

        return new Student(
                doc.getString("student_no"),
                doc.getString("name"),
                doc.getString("department")
        );
    }
}
