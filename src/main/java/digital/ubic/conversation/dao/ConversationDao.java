package digital.ubic.conversation.dao;

public interface ConversationDao {

    void saveContext(String json, Long userId);

    String getLastContext(String json);

}
