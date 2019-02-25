package digital.ubic.conversation.filtering.model;

import java.util.Arrays;

public class FilteringContains implements FilteringType {

    public static final String TYPE = "contains";

    private String name;
    private String weight;

    public FilteringContains(String name, String weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public Double getWeight() {
        return Double.valueOf(weight);
    }

    public String getType() {
        return TYPE;
    }

    @Override
    public Double calculate(String productValue, String userValue) {
        boolean inRange = Arrays.asList(productValue.split(",")).stream().anyMatch(s -> s.equalsIgnoreCase(userValue));
        Double calc = inRange ? 1D * getWeight() : 0;
        return calc;
    }
}
