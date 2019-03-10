package digital.ubic.conversation.controller;

import digital.ubic.conversation.dao.mongo.FilteringDaoMongo;
import digital.ubic.conversation.filtering.model.FilteringType;
import digital.ubic.conversation.controller.request.ConversationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filtering")
public class FilteringController {

    @Autowired
    private FilteringDaoMongo dao;

    @GetMapping("/list")
    @ResponseBody
    public ResponseEntity<List<FilteringType>> list() {
        return ResponseEntity.ok(dao.getFilteringTypes());
    }

}
