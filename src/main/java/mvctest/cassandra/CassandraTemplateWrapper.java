package mvctest.cassandra;

import org.springframework.data.cassandra.convert.MappingCassandraConverter;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.mapping.BasicCassandraMappingContext;
import org.springframework.data.cassandra.mapping.CassandraMappingContext;


public class CassandraTemplateWrapper extends CassandraTemplate {

    public CassandraTemplateWrapper() {
        this.setConverter(new MappingCassandraConverter());
    }

    @Override
    public void afterPropertiesSet() {}
}
