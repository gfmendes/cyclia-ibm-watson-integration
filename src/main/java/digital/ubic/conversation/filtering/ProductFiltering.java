package digital.ubic.conversation.filtering;

import digital.ubic.conversation.filtering.model.FilteringType;
import digital.ubic.conversation.filtering.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductFiltering {

    @Autowired
    private List<FilteringType> filteringTypes;

    public List<Product> filter(List<Product> products, Map<String, String> answers) {

        if (!answers.isEmpty()) {
            for (Product p : products) {
                p.setFilteringValue(calculateFiltersValue(p, answers));
            }
            products.sort(Comparator.comparing(Product::getFilteringValue).reversed());
        }
        return products;
    }

    private Double calculateFiltersValue(Product product, Map<String, String> answers) {

        Double recommendationSum = 0D;
        Map<String, String> productFeatures = product.getFeatures();
        for (Map.Entry<String, String> f : productFeatures.entrySet()) {
            final String featureName = f.getKey();
            final String featureValue = f.getValue();
            final String answerValue = answers.get(featureName);

            if (answerValue != null) {
                Optional<FilteringType> type = getFilteringTypes()
                        .stream()
                        .filter(p -> p.getName().equals(featureName))
                        .findFirst();

                if (type.isPresent()) {
                    Double calc = type.get().calculate(featureValue, answerValue);
                    recommendationSum += calc;
                    System.out.println(String.format("Product=%s, feature=%s, answer=%s, calc=%s",
                            product.getName(),
                            featureName,
                            answers.get(featureName),
                            calc));
                }
            }
        }

        return recommendationSum;
    }

    public List<FilteringType> getFilteringTypes() {
        return filteringTypes;
    }
}
