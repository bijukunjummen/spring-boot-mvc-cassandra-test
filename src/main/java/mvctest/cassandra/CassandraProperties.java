package mvctest.cassandra;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;


@ConfigurationProperties(prefix = "cassandra")
@Component
public class CassandraProperties {

    private boolean overrideEurekaLookup;
    private List<String> contactPoints;
    private int cqlPort = 9042;
    private String clusterName;
    private String keyspace;

    public boolean isOverrideEurekaLookup() {
        return overrideEurekaLookup;
    }

    public void setOverrideEurekaLookup(boolean overrideEurekaLookup) {
        this.overrideEurekaLookup = overrideEurekaLookup;
    }

    public List<String> getContactPoints() {
        return contactPoints;
    }

    public void setContactPoints(List<String> contactPoints) {
        this.contactPoints = contactPoints;
    }

    public int getCqlPort() {
        return cqlPort;
    }

    public void setCqlPort(int cqlPort) {
        this.cqlPort = cqlPort;
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public String getKeyspace() {
        return keyspace;
    }

    public void setKeyspace(String keyspace) {
        this.keyspace = keyspace;
    }

}