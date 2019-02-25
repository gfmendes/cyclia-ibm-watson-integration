package digital.ubic.conversation.dao;

import java.util.Map;

public interface AnswerDao {

    void save(String conversationId, String type, String value);

    Map<String, String> get(String conversationId);
}
