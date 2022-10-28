package com.lhj8390.dashboard.mapper;

import com.lhj8390.dashboard.model.dto.user.JoinRequestDTO;
import com.lhj8390.dashboard.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", ignore = true)
    User toEntity(JoinRequestDTO dto);
}
