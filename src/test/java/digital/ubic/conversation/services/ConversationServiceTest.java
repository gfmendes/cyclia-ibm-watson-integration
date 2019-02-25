package digital.ubic.conversation.services;


import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

@Ignore
@RunWith(SpringRunner.class)
public class ConversationServiceTest {

    @InjectMocks
    private ConversationService service;

    @Test
    public void sendMessage() {
        service.message("message", 123L, "b5a027b8-f7b2-4e0e-803e-7e164be741ef");
    }

}
