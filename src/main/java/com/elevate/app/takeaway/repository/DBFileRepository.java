package com.elevate.app.takeaway.repository;

import com.elevate.app.takeaway.model.DBFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DBFileRepository extends JpaRepository<DBFile, String> {

    Optional<List<DBFile>> findByUserId(long userId);
}
