package digital.ubic.conversation.dao.mongo;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import digital.ubic.conversation.dao.FilteringDao;
import digital.ubic.conversation.filtering.model.*;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class FilteringDaoMongo implements FilteringDao {

    public static final String FILTERING_TYPE_COLLECTION_NAME = "filtering_type";

    @Autowired
    private MongoTemplate template;

    public List<FilteringType> getFilteringTypes() {
        FindIterable<Document> docs = template.getDb().getCollection(FILTERING_TYPE_COLLECTION_NAME).find();
        MongoCursor<Document> documentCursor = docs.iterator();
        List<FilteringType> types = new ArrayList<>();
        while (documentCursor.hasNext()) {
            Document doc = documentCursor.next();
            FilteringType filteringType = getFilteringTypeInstance(doc);
            types.add(filteringType);
        }
        return types;
    }

    private FilteringType getFilteringTypeInstance(Document doc) {
        String type = (String) doc.get("type");
        String name = (String) doc.get("name");
        String weight = (String) doc.get("weight");
        FilteringType filteringType = null;
        if (type.equals(FilteringExact.TYPE)) {
            filteringType = new FilteringExact(name, weight);
        } else if (type.equals(FilteringContains.TYPE)) {
            filteringType = new FilteringContains(name, weight);
        } else if (type.equals(FilteringInterval.TYPE)) {
            filteringType = new FilteringInterval(name, weight);
        } else if (type.equals(FilteringMargin.TYPE)) {
            String margin = (String) doc.get("margin");
            filteringType = new FilteringMargin(name, margin, weight);
        } else if (type.equals(FilteringLowerMargin.TYPE)) {
            String margin = (String) doc.get("margin");
            filteringType = new FilteringLowerMargin(name, margin, weight);
        } else if (type.equals(FilteringUpperMargin.TYPE)) {
            String margin = (String) doc.get("margin");
            filteringType = new FilteringUpperMargin(name, margin, weight);
        }

        return filteringType;
    }

}