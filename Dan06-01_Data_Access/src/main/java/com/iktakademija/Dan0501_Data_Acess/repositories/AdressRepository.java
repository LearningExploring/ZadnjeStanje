package com.iktakademija.Dan0501_Data_Acess.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iktakademija.Dan0501_Data_Acess.entities.AdressEntity;

public interface AdressRepository extends CrudRepository<AdressEntity, Integer> {
// Long je bolji od integera  u ovom slucaju
}
