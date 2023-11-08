package com.example.gogotrips.shared.mappers;

import com.example.gogotrips.shared.dto.UserDTO;
import com.example.gogotrips.shared.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private final ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public User mapToModel(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    public UserDTO mapToDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }
}
