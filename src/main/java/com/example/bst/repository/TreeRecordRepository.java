package com.example.bst.repository;

import com.example.bst.model.TreeRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreeRecordRepository extends JpaRepository<TreeRecord, Long> {
}
