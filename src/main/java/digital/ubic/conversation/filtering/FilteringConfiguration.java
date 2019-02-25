package digital.ubic.conversation.filtering;

import digital.ubic.conversation.dao.FilteringDao;
import digital.ubic.conversation.filtering.model.FilteringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class FilteringConfiguration {

    @Autowired
    private FilteringDao filteringDao;

    @Bean
    public List<FilteringType> filteringTypes() {
        return filteringDao.getFilteringTypes();
    }


}
