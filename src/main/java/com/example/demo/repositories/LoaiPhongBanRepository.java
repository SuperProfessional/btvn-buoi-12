package com.example.demo.repositories;

import com.example.demo.entity.LoaiPhongBan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoaiPhongBanRepository extends JpaRepository<LoaiPhongBan, Integer> {
}
