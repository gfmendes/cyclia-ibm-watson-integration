package digital.ubic.conversation.services.filtering;


import digital.ubic.conversation.filtering.ProductFiltering;
import digital.ubic.conversation.filtering.model.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FilteringServiceTest {

    @Autowired
    private ProductFiltering filtering;

    @Test
    public void filter() {

        System.out.println(filtering.filter(getProducts(), getAnswers()));

    }


    private static Map<String, String> getAnswers() {
        Map<String, String> answers = new HashMap<>();
        answers.put("local", "city");
        answers.put("price", "4200");
        answers.put("height", "170");
        answers.put("newType", "1000");

        return answers;
    }

    private static List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        Map<String, String> features = new HashMap<>();
        features.put("local", "city");
        features.put("price", "3500");
        features.put("height", "170;200");
        features.put("newType", "0");
        Product product = new Product("Bike1", features);
        products.add(product);

        Map<String, String> features2 = new HashMap<>();
        features2.put("local", "trail");
        features2.put("price", "7000");
        features2.put("height", "130;170");
        features2.put("newType", "2000");
        Product product2 = new Product("Bike2", features2);
        products.add(product2);

        Map<String, String> features3 = new HashMap<>();
        features3.put("local", "trail");
        features3.put("price", "3500");
        features3.put("height", "150;190");
        features3.put("newType", "1000");
        Product product3 = new Product("Bike3", features3);
        products.add(product3);

        return products;
    }


}
