package digital.ubic.conversation.controller;

import digital.ubic.conversation.controller.request.ConversationRequest;
import digital.ubic.conversation.filtering.model.Product;
import digital.ubic.conversation.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    /*
    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<String> create(@RequestBody ConversationRequest conversation) {
        String response = service.message(conversation.getMessage(), conversation.getUserId(), conversation.getConversationId());
        //return ResponseEntity.ok(response);
    }


    @PostMapping("/update")
    @ResponseBody
    public ResponseEntity<String> update(@RequestBody OptionRequest option) {
        String response = service.messageOption(option.getMessage(), option.getType(), option.getUserId(), option.getConversationId());
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/delete")
    @ResponseBody
    public ResponseEntity<List<Product>> delete(@RequestBody ConversationRequest conversation) {
        List<Product> response = service.offerProduct(conversation.getMessage(), conversation.getUserId(), conversation.getConversationId());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get")
    @ResponseBody
    public ResponseEntity<List<Product>> get(@RequestBody ConversationRequest conversation) {
        List<Product> response = service.offerProduct(conversation.getMessage(), conversation.getUserId(), conversation.getConversationId());
        return ResponseEntity.ok(response);
    }

*/
    @GetMapping("/list")
    @ResponseBody
    public ResponseEntity<List<Product>> list() {
        List<Product> response = service.getAll();
        return ResponseEntity.ok(response);
    }


}
