package digital.ubic.conversation.dao;

import digital.ubic.conversation.filtering.model.FilteringType;

import java.util.List;

public interface FilteringDao {

    public List<FilteringType> getFilteringTypes();

}
