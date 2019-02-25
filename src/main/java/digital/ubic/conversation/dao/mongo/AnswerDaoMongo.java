package digital.ubic.conversation.dao.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import digital.ubic.conversation.dao.AnswerDao;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class AnswerDaoMongo implements AnswerDao {

    @Autowired
    private MongoTemplate template;

    @Override
    public void save(String conversationId, String type, String value) {

        BasicDBObject query = new BasicDBObject("conversation_id", conversationId);
        UpdateOptions updateOptions = new UpdateOptions().upsert(true);

        Map map = new HashMap();
        map.put("type", type);
        map.put("value", value);
        template.getDb().getCollection("product_answer").updateOne(
                query,
                Updates.addToSet("answers", map),
                updateOptions);
    }

    @Override
    public Map<String, String> get(String conversationId) {
        BasicDBObject query = new BasicDBObject("conversation_id", conversationId);
        FindIterable<Document> doc = template.getDb()
                .getCollection("product_answer")
                .find(query);

        Map<String, String> map = new HashMap<>();
        if (doc.first() != null) {
            map = getAnswerMap(doc);
        }

        return map;
    }

    private Map<String, String> getAnswerMap(FindIterable<Document> doc) {
        List<Document> answers = (List<Document>) doc.first().get("answers", ArrayList.class);
        return answers.stream()
                .collect
                        (Collectors.toMap(
                                k -> k.getString("type"),
                                v -> v.getString("value")));
    }
}
