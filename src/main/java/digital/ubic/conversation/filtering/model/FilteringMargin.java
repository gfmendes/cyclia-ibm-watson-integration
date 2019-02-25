package digital.ubic.conversation.filtering.model;

public class FilteringMargin implements FilteringType {

    public static final String  TYPE = "margin";

    private String name;
    private String margin;
    private String weight;

    public FilteringMargin(String name, String margin, String weight) {
        this.name = name;
        this.margin = margin;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public String getMargin() {
        return margin;
    }

    public Double getWeight() {
        return Double.valueOf(weight);
    }

    public String getType() {
        return TYPE;
    }

    @Override
    public Double calculate(String productValue, String userValue) {
        Double featureValueDouble = new Double(productValue);
        Double margin = new Double(getMargin()) / 100;
        Double min = featureValueDouble * (1 - margin);
        Double max = featureValueDouble * (1 + margin);
        Double answer = Double.valueOf(userValue);
        Double calc = (answer >= min && answer <= max) ? 1D * getWeight() : 0;
       // System.out.println(String.format("Feature value = %s, User value = %s, Result = %s", productValue, userValue, calc));
        return calc;
    }
}
