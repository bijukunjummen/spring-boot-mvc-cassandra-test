package mvctest.service;

import mvctest.domain.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class CassandraHotelRepository implements HotelRepository {

    @Autowired
    private CassandraTemplate cassandraTemplate;

    @Override
    public Hotel findOne(UUID id) {
        return cassandraTemplate.selectOneById(Hotel.class, id);
    }

    @Override
    public List<Hotel> findAll() {
        return cassandraTemplate.selectAll(Hotel.class);
    }

    @Override
    public Hotel save(Hotel hotel) {
        UUID id = UUID.randomUUID();
        hotel.setId(id);
        return cassandraTemplate.insert(hotel);
    }

    @Override
    public Hotel update(Hotel hotel) {
        return cassandraTemplate.update(hotel);
    }

    @Override
    public void delete(UUID id) {
        cassandraTemplate.deleteById(Hotel.class, id);
    }
}
