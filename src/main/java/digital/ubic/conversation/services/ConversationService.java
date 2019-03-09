package digital.ubic.conversation.services;

import digital.ubic.conversation.dao.AnswerDao;
import digital.ubic.conversation.filtering.ProductFiltering;
import digital.ubic.conversation.dao.ProductDao;
import digital.ubic.conversation.filtering.model.Product;
import digital.ubic.conversation.dao.ConversationDao;
import digital.ubic.conversation.watson.WatsonAssistantV1;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConversationService {

    @Autowired
    private WatsonAssistantV1 assistant;

    @Autowired
    private ConversationDao conversationDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private AnswerDao answerDao;

    @Autowired
    private ProductFiltering productFiltering;

    public String message(String message, Long userId, String conversationId) {

        String context = null;
        if (StringUtils.isNotBlank(conversationId)) {
            context = conversationDao.getLastContext(conversationId);
        }
        String response = assistant.sendMessage(message, context);
        conversationDao.saveContext(response, userId);
        return response;

    }

    public String messageOption(String message, String type, Long userId, String conversationId) {
        answerDao.save(conversationId, type, message);
        return message(message, userId, conversationId);
    }

    public void option(String message, String type, Long userId, String conversationId) {
        answerDao.save(conversationId, type, message);
    }

    public List<Product> offerProduct(String message, Long userId, String conversationId) {

        return productFiltering.filter(productDao.findAll(), answerDao.get(conversationId));
    }
}
