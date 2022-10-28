package com.lhj8390.dashboard.repository;

import com.lhj8390.dashboard.model.RoleType;
import com.lhj8390.dashboard.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleType name);
}
