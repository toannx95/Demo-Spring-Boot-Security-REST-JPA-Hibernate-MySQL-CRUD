package com.s3corp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.s3corp.entity.Layout;

@Repository
public interface LayoutRepository extends JpaRepository<Layout, Integer> {

}
