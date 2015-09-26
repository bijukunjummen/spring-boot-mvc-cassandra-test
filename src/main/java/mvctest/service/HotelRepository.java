package mvctest.service;

import mvctest.domain.Hotel;
import java.util.List;
import java.util.UUID;

public interface HotelRepository {
	Hotel findOne(UUID id);

	List<Hotel> findAll();

	Hotel save(Hotel hotel);

	Hotel update(Hotel hotel);

	void delete(UUID id);
}
