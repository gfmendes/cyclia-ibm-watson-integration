package digital.ubic.conversation.dao;

import digital.ubic.conversation.filtering.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductDao extends MongoRepository<Product,String> {

}
