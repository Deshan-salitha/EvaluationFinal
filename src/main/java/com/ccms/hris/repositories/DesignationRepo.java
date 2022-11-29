package com.ccms.hris.repositories;

import com.ccms.hris.models.entities.Designation;
import com.ccms.hris.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignationRepo  extends JpaRepository<Designation, Long> {
}
