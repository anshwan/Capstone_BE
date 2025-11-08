package com.example.capstone.model.repository;

import com.example.capstone.model.entity.Model;
import com.example.capstone.model.entity.ReleaseNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReleaseNoteRepository extends JpaRepository<ReleaseNote, Long> {
    void deleteAllByModel(Model model);
}