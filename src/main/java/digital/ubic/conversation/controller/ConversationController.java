package digital.ubic.conversation.controller;

import digital.ubic.conversation.controller.request.ConversationRequest;
import digital.ubic.conversation.controller.request.OptionRequest;
import digital.ubic.conversation.services.ConversationService;
import digital.ubic.conversation.filtering.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assistant")
public class ConversationController {

    @Autowired
    private ConversationService service;

    @PostMapping("/message")
    @ResponseBody
    public ResponseEntity<String> message(@RequestBody ConversationRequest conversation) {
        String response = service.message(conversation.getMessage(), conversation.getUserId(), conversation.getConversationId());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/messageOption")
    @ResponseBody
    public ResponseEntity<String> messageOption(@RequestBody OptionRequest option) {
        String response = service.messageOption(option.getMessage(), option.getType(), option.getUserId(), option.getConversationId());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/option")
    public void option(@RequestBody OptionRequest option) {
        service.messageOption(option.getMessage(), option.getType(), option.getUserId(), option.getConversationId());
    }

    @PostMapping("/offerProduct")
    @ResponseBody
    public ResponseEntity<List<Product>> offerProduct(@RequestBody ConversationRequest conversation) {
        List<Product> response = service.offerProduct(conversation.getMessage(), conversation.getUserId(), conversation.getConversationId());
        return ResponseEntity.ok(response);
    }
}
