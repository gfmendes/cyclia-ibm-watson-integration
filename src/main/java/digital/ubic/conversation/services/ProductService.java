package digital.ubic.conversation.services;

import digital.ubic.conversation.filtering.ProductFiltering;
import digital.ubic.conversation.dao.ProductDao;
import digital.ubic.conversation.filtering.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    @Autowired
    private ProductFiltering productFiltering;

    @Autowired
    private ProductDao productDao;

    public void offerProduct(Long userId, String conversationId) {
        Map<String, String> answers = null;
        //productFiltering.filter(productDao.getAll(), answers);
    }

    public void add(Product product) {
        //productDao.add(product);
    }

    public List<Product> getAll() {
        return productDao.getAll();
    }
}
