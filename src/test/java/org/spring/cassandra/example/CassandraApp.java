package org.spring.cassandra.example;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import mvctest.domain.Hotel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;

public class CassandraApp {

    private static final Logger LOG = LoggerFactory.getLogger(CassandraApp.class);

    private static Cluster cluster;
    private static Session session;

    public static void main(String[] args) {

//        try {

        cluster = Cluster.builder().addContactPoints("localhost").build();
        Metadata metadata = cluster.getMetadata();
        System.out.printf("Connected to cluster: %s\n",
                metadata.getClusterName());
        for (Host host : metadata.getAllHosts()) {
            System.out.printf("Datacenter: %s; Host: %s; Rack: %s\n",
                    host.getDatacenter(), host.getAddress(), host.getRack());
        }

        session = cluster.connect("mykeyspace");





        CassandraOperations cassandraOps = new CassandraTemplate(session);

//        Hotel hotel = new Hotel();
//        hotel.setId(1);
//        hotel.setName("Hotel1");
//        hotel.setAddress("Hotel Address 1");
//        hotel.setZip("Hotel Zip 1");
//        hotel.setVersion(1);
//
//        cassandraOps.insert(hotel);
//
            Select s = QueryBuilder.select().from("hotels");

            s.where(QueryBuilder.eq("id", 1));

        Hotel hotelResponse = cassandraOps.selectOne("select * from hotels where id=1", Hotel.class);

        LOG.info(hotelResponse + "");
//
////            cassandraOps.truncate("hotel");
//
////        } catch (UnknownHostException e) {
////            e.printStackTrace();
////        }

        cluster.close();

    }
}