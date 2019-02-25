package digital.ubic.conversation.dao.mongo;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import digital.ubic.conversation.dao.ProductDao;
import digital.ubic.conversation.filtering.model.Product;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class ProductDaoMongo implements ProductDao {

    @Autowired
    private MongoTemplate template;

    @Override
    public List<Product> getAll() {
        FindIterable<Document> docs = template.getDb().getCollection("product").find();
        MongoCursor<Document> documentCursor = docs.iterator();
        List<Product> products = new ArrayList<>();
        while (documentCursor.hasNext()) {
            Document doc = documentCursor.next();
            String name = doc.getString("name");
            List<Document> features = doc.get("features", ArrayList.class);
            Map<String, String> map = features.stream()
                    .collect
                            (Collectors.toMap(
                                    k -> k.getString("type"),
                                    v -> v.getString("value")));
            Product product = new Product(name, map);
            product.setUrl(doc.getString("URL"));
            product.setSponsored(doc.getBoolean("sponsored"));
            products.add(product);
        }
        return products;
    }

}
