import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDBClient {
    public static void main(String[] args) {
        MongoClient mClient = MongoClients.create(
                "mongodb://admin:password@localhost:27017"
        );
        MongoDatabase db = mClient.getDatabase("store_database");
        MongoCollection<Document> collection = db.getCollection("products");

        Document d = new Document("name", "iPhone 15").append("price", 90_000)
                .append("inStock", true);
        collection.insertOne(d);
        System.out.println(d);
    }
}
