package digital.ubic.conversation.filtering.model;

import java.util.Map;

public class Product {

    private String name;
    private Map<String, String> features;
    private Double filteringValue;
    private String url;
    private Boolean sponsored;

    public Product(String name, Map<String, String> features) {
        this.name = name;
        this.features = features;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getFeatures() {
        return features;
    }

    public void setFeatures(Map<String, String> features) {
        this.features = features;
    }

    public Double getFilteringValue() {
        return filteringValue;
    }

    public void setFilteringValue(Double filteringValue) {
        this.filteringValue = filteringValue;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getSponsored() {
        return sponsored;
    }

    public void setSponsored(Boolean sponsored) {
        this.sponsored = sponsored;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", features=" + features +
                ", filteringValue=" + filteringValue +
                ", url='" + url + '\'' +
                ", sponsored=" + sponsored +
                '}';
    }
}
