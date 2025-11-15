package util;

import com.mongodb.client.*;
public class MongoDBUtil {
    static MongoClient client = MongoClients.create("mongodb://localhost:27017");
    static MongoDatabase db = client.getDatabase("socialapp");
    public static MongoDatabase getDatabase() { return db; }
}
