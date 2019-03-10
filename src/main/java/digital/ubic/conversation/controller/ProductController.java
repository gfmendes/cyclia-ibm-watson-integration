package digital.ubic.conversation.controller;

import digital.ubic.conversation.filtering.model.Product;
import digital.ubic.conversation.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;


    @PostMapping("/")
    @ResponseBody
    public ResponseEntity save(@RequestBody Product product) throws URISyntaxException {
        Product productSaved = service.save(product);
        return ResponseEntity.created(new URI("/")).body(productSaved);
    }

/*

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

    @GetMapping("/")
    @ResponseBody
    public ResponseEntity<List<Product>> list() {
        List<Product> response = service.findAll();
        return ResponseEntity.ok(response);
    }


}
