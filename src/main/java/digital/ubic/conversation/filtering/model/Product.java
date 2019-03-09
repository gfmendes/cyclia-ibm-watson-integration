package digital.ubic.conversation.filtering.model;

import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.Map;

public class Product {

    @Id
    private String id;
    private String name;
    private List<Feature> features;
    private Double filteringValue;
    private String URL;
    private Boolean sponsored;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    public Double getFilteringValue() {
        return filteringValue;
    }

    public void setFilteringValue(Double filteringValue) {
        this.filteringValue = filteringValue;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
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
                ", url='" + URL + '\'' +
                ", sponsored=" + sponsored +
                '}';
    }
}
