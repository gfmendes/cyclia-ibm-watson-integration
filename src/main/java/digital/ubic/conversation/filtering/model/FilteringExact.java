package digital.ubic.conversation.filtering.model;

public class FilteringExact implements FilteringType {

    public static final String TYPE = "exact";

    private String name;
    private String weight;

    public FilteringExact(String name, String weight) {
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
        Double calc = productValue.equalsIgnoreCase(userValue) ? 1D * getWeight() : 0;
       // System.out.println(String.format("Feature value = %s, User value = %s, Result = %s", productValue, userValue, calc));
        return calc;
    }
}
