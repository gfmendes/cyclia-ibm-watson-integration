package digital.ubic.conversation.api;

import digital.ubic.conversation.controller.ConversationController;
import digital.ubic.conversation.controller.request.ConversationRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ConversationControllerTest {

    @InjectMocks
    private ConversationController controller;

    @Test
    public void sendMessage() {
        ConversationRequest request = new ConversationRequest();
        request.setConversationId("b5a027b8-f7b2-4e0e-803e-7e164be741ef");
        request.setMessage("message");
        request.setUserId(123L);
        //controller.message(request);
    }

}
