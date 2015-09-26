package mvctest.cassandra;

import com.datastax.driver.core.Cluster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

@Component
public class EurekaClusterBuilder extends Cluster.Builder {

    private final DiscoveryClient discoveryClient;

    private final CassandraProperties cassandraProperties;

    private static final Logger LOGGER = LoggerFactory.getLogger(EurekaClusterBuilder.class);

    @Autowired
    public EurekaClusterBuilder(DiscoveryClient discoveryClient, CassandraProperties cassandraProperties) {
        this.discoveryClient = discoveryClient;
        this.cassandraProperties = cassandraProperties;
    }

    @Override
    public List<InetSocketAddress> getContactPoints() {

        if (cassandraProperties.isOverrideEurekaLookup()) {
            return cassandraProperties.getContactPoints()
                    .stream()
                    .map(contactPointToSocket())
                    .collect(toList());
        } else {
            return toEurekaInstances(discoveryClient, cassandraProperties.getClusterName())
                    .stream().map(eurekaInstancesToSocket())
                    .collect(toList());
        }
    }

    protected List<ServiceInstance> toEurekaInstances(DiscoveryClient discoveryClient, final String serviceId) {
        return discoveryClient.getInstances(serviceId);
    }

    protected final Function<String, InetSocketAddress> contactPointToSocket() {
        return (String str) -> {
            InetSocketAddress address = new InetSocketAddress(str, cassandraProperties.getCqlPort());
            LOGGER.info("Adding contact point {}", address);
            return address;
        };
    }

    protected final Function<ServiceInstance, InetSocketAddress> eurekaInstancesToSocket() {
       return instanceInfo -> {
                final String localAddress = instanceInfo.getHost();
                LOGGER.info("Adding contact point {}:{}", localAddress, instanceInfo.getPort());
                final InetSocketAddress address = new InetSocketAddress(localAddress, instanceInfo.getPort());
                return address;
            };
    }
}