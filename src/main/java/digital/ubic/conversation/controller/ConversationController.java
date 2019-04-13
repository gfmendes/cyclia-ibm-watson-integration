package digital.ubic.conversation.controller;

import digital.ubic.conversation.controller.request.ConversationRequest;
import digital.ubic.conversation.controller.request.OptionRequest;
import digital.ubic.conversation.services.ConversationService;
import digital.ubic.conversation.filtering.model.Product;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/assistant")
public class ConversationController {

    @Autowired
    private ConversationService service;

    @ApiOperation(
            value = "Sends a message to Watson Assistant.",
            notes = "To create a new conversation, send 'conversationId' and 'message' as empty strings. <br/>" +
            "To resume a conversation,  fill 'conversationId', 'message' and 'userId' with valid values. <br/>" +
            "API returns the full watson assistant response of current chat iteration." )
    @PostMapping("/message")
    @ResponseBody
    public ResponseEntity<String> message(@RequestBody ConversationRequest conversation) {
        String response = service.message(conversation.getMessage(), conversation.getUserId(), conversation.getConversationId());
        return ResponseEntity.ok(response);
    }

    @ApiOperation(
            value = "Sends a option answer message to Watson Assistant and save this answer in Cyclia database.",
            notes = "To resume a conversation, fill 'conversationId' and 'userId' with valid values. <br/>" +
                    "'message' must have the answer (text from input object) choose by the user <br/> " +
                    "'type' must have the type (price, height, sex, local, etc) of question being answered. <br/>" +
                    "API returns the full watson assistant response of current chat iteration." )
    @PostMapping("/messageOption")
    @ResponseBody
    public ResponseEntity<String> messageOption(@RequestBody OptionRequest option) {
        String response = service.messageOption(option.getMessage(), option.getType(), option.getUserId(), option.getConversationId());
        return ResponseEntity.ok(response);
    }

    @ApiOperation(
            value = "Save an answer in Cyclia database. Does not iterate with Watson",
            notes = "Fill 'conversationId' with a valid value. <br/>" +
                    "'message' must have the answer (text from input object) choose by the user <br/> " +
                    "'type' must have the type (price, height, sex, local, etc) of question being answered. <br/>")
    @PostMapping("/option")
    public ResponseEntity<?> option(@RequestBody OptionRequest option) throws URISyntaxException {
        service.option(option.getMessage(), option.getType(), option.getConversationId());
        return ResponseEntity.created(new URI("/product_answer")).body(option);
    }

    @ApiOperation(
            value = "Based on previous answers of a conversation, offers a list of products",
            notes = "Fill 'conversationId' with a valid value. <br/>")
    @GetMapping("/offerProduct/{conversationId}")
    @ResponseBody
    public ResponseEntity<List<Product>> offerProduct(@PathVariable("conversationId") String conversationId) {
        List<Product> response = service.offerProduct(conversationId);
        return ResponseEntity.ok(response);
    }
}
