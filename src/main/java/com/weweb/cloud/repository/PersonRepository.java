package com.weweb.cloud.repository;

import com.weweb.cloud.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jackshen on 2017/3/27.
 */
@Repository
public interface PersonRepository extends JpaRepository<Person,Long> {
}
