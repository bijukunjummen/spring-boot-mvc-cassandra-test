package mvctest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class SampleCommandLineRunner implements CommandLineRunner {

    @Autowired
    private DiscoveryClient discoveryClient;

    @PostConstruct
    public void postConstruct() {
//        System.out.println("Printing from postConstruct");
//        printDiscoveredNodes();
    }

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("Printing from run method");
        printDiscoveredNodes();
    }

    public void printDiscoveredNodes() {
        System.out.println(" Printing Discovered Nodes ");

        for (ServiceInstance instance: discoveryClient.getInstances("samplecassandra.vip")) {
            System.out.println("Host: Port = " + instance.getHost() + ":" + instance.getPort());
        }
    }
}
