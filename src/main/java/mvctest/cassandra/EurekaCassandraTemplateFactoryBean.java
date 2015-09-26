package mvctest.cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.SmartLifecycle;
import org.springframework.core.Ordered;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.stereotype.Component;

@Component("cassandraTemplate")
public class EurekaCassandraTemplateFactoryBean implements SmartLifecycle, FactoryBean<CassandraTemplate>, Ordered {

    private static final Logger LOGGER = LoggerFactory.getLogger(EurekaCassandraTemplateFactoryBean.class);

    private final EurekaClusterBuilder eurekaClusterBuilder;
    private final CassandraProperties cassandraProperties;
    private final CassandraTemplateWrapper cassandraTemplateWrapper;

    private boolean running;

    @Autowired
    public EurekaCassandraTemplateFactoryBean(
                                 EurekaClusterBuilder eurekaClusterBuilder,
                                 CassandraProperties cassandraProperties) {
        this.eurekaClusterBuilder = eurekaClusterBuilder;
        this.cassandraProperties = cassandraProperties;
        this.cassandraTemplateWrapper = new CassandraTemplateWrapper();
    }

    @Override
    public boolean isAutoStartup() {
        return true;
    }

    @Override
    public void stop(Runnable callback) {

    }

    @Override
    public void start() {
        LOGGER.info("About to start Discovery client lookup of Cassandra Cluster!!!");
        final Cluster cluster = this.eurekaClusterBuilder.build();
        Session session = cluster.connect(this.cassandraProperties.getKeyspace());
        this.cassandraTemplateWrapper.setSession(session);
        LOGGER.info("Completed Discovery client lookup of Cassandra Cluster!!!");
        running = true;
    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isRunning() {
        return this.running;
    }

    @Override
    public int getPhase() {
        return Integer.MAX_VALUE;
    }

    @Override
    public int getOrder() {
        return 1;
    }

    @Override
    public CassandraTemplate getObject() throws Exception {
        return this.cassandraTemplateWrapper;
    }

    @Override
    public Class<?> getObjectType() {
        return CassandraTemplate.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}