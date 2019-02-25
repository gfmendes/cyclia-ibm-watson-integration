package digital.ubic.conversation.filtering.model;

public class FilteringInterval implements FilteringType {

    public static final String TYPE = "interval";

    private String name;
    private String weight;

    public FilteringInterval(String name, String weight) {
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
        Integer min = Integer.valueOf(productValue.split(";")[0]);
        Integer max = Integer.valueOf(productValue.split(";")[1]);
        Integer answer = Integer.valueOf(userValue);
        Double calc = (answer >= min && answer <= max) ? 1D * getWeight() : 0;
        //System.out.println(String.format("Feature value = %s, User value = %s, Result = %s", productValue, userValue, calc));
        return calc;

    }
}
