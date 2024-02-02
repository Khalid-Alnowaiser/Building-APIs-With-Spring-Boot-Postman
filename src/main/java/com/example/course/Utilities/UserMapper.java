package com.example.course.Utilities;

import com.example.course.DTOs.UserDTO;
import com.example.course.Entities.UserEntity;

public class UserMapper {
    public static UserDTO userToUserDto(UserEntity user){
        try {
            UserDTO userDTO = new UserDTO(
                    user.getUserId(),
                    user.getEmail(),
                    user.getName(),
                    user.getStatus()
            );
            return userDTO;
        }catch (Exception e){
            System.out.printf("###################\n\nError caused by UserMapper userToUserDTO method\n\n"+e.toString()+"\n\n\n#######################");
            return null;
        }
    }
}
