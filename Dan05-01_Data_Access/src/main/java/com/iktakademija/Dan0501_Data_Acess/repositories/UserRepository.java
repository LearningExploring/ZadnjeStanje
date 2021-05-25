package com.iktakademija.Dan0501_Data_Acess.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.iktakademija.Dan0501_Data_Acess.entities.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {

	UserEntity findByEmail(String email);	
	List<UserEntity> findAllByNameOrderByEmailAsc(String name);
	
	Optional<UserEntity> findById(Integer id);
	
//	@Query("select * from UserEntity where name = name")
//	List<UserEntity> findAllByName2(String name);
	
	
}
