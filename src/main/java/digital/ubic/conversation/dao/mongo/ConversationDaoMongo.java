package digital.ubic.conversation.dao.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import digital.ubic.conversation.dao.ConversationDao;
import org.apache.commons.lang3.StringUtils;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class ConversationDaoMongo implements ConversationDao {

    @Autowired
    private MongoTemplate template;

    @Override
    public void saveContext(String json, Long userId) {
        Document doc = Document.parse(json);
        doc.append("user_id", userId);
        doc.append("chat_date", new Date());
        template.getDb().getCollection("watson_chat_response").insertOne(doc);
    }

    @Override
    public String getLastContext(String conversationId) {
        String response = null;
        if (StringUtils.isNotBlank(conversationId)) {
            BasicDBObject query = new BasicDBObject("context.conversation_id", conversationId);
            FindIterable<Document> docs = template.getDb()
                                                  .getCollection("watson_chat_response")
                                                  .find(query)
                                                  .sort(new BasicDBObject("chat_date", -1))
                                                  .limit(1);
            if (docs.first() != null) {
                System.out.println(docs.first().toString());
                response = com.mongodb.util.JSON.serialize(docs.first().get("context"));
            }
        }
        return response;
    }
}