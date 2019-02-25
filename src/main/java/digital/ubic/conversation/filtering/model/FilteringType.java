package digital.ubic.conversation.filtering.model;

public interface FilteringType {

    public Double calculate(String productValue, String userValue);

    public String getName();

    public String getType();

}
