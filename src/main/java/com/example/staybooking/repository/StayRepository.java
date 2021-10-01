package com.example.staybooking.repository;

import com.example.staybooking.model.Stay;
import com.example.staybooking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StayRepository extends JpaRepository<Stay, Long> {
    //extend JpaRepository的原因是自带了一些操作数据库的方法了，比如findbyID,deleteById等等

    List<Stay> findByHost(User user);
}
