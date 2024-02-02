package com.example.course.Services;
import com.example.course.DTOs.UserDTO;
import com.example.course.DAOs.UserRepository;
import com.example.course.Entities.UserEntity;
import com.example.course.Utilities.UserMapper;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor

@Service
public class UserService {

    private final UserRepository userRepository;

    public ResponseEntity<Object> createUser(UserEntity userEntity) {
        try {
            if (userRepository.findByUserId(userEntity.getUserId()) != null) {
                return new ResponseEntity<>("User with user id: " + userEntity.getUserId() + " already exists", HttpStatus.CONFLICT);
            } else {
                userEntity.setStatus("Active");
                userRepository.save(userEntity);
                return new ResponseEntity<>("Successfully added User", HttpStatus.CREATED);
            }

        } catch (Exception e) {
            System.out.println(e.toString());
            return new ResponseEntity<>("An unexpected error has occurred, please check the logs for more information", HttpStatus.BAD_REQUEST);
        }

    }
    public ResponseEntity<Object> updateUser(UserEntity userEntity) {
        try{
            UserEntity userToUpdate = userRepository.findByUserId(userEntity.getUserId());
            if(userToUpdate == null){
                return new ResponseEntity<>("User with UserID: "+userEntity.getUserId()+" doesn't exist",HttpStatus.NOT_FOUND);
            }if(userEntity.getAge()!=0){
                userToUpdate.setAge(userEntity.getAge());
            }if(userEntity.getName()!=null && !userEntity.getName().isBlank()){
                userToUpdate.setName(userEntity.getName());
            }if(userEntity.getEmail()!=null && !userEntity.getEmail().isBlank()){
                userToUpdate.setEmail(userEntity.getEmail());
            }if(userEntity.getPassword()!=null && !userEntity.getPassword().isBlank()){
                userToUpdate.setPassword(userEntity.getPassword());
            }
            userRepository.save(userToUpdate);
            return new ResponseEntity<>("Successfully updated user",HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.toString());
            return new ResponseEntity<>("An unexpected error has occurred, please check the logs for more info",HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity<Object> deleteUser(UUID userId) {
        try{
            UserEntity userEntity = userRepository.findByUserId(userId);
            if(userEntity==null){
                return new ResponseEntity<>("User with UserID: "+userEntity.getUserId()+" doesn't exist",HttpStatus.NOT_FOUND);
            }if(userEntity.getStatus().equals("Deactivated"))
                return new ResponseEntity<>("User with UserID: "+userEntity.getUserId()+" has already been deleted",HttpStatus.BAD_REQUEST);
            userEntity.setStatus("Deactivated");
            userRepository.save(userEntity);
            return new ResponseEntity<>("Successfully deleted user with Id: "+userId,HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.toString());
            return new ResponseEntity<>("An unexpected error has occurred, please check the logs for more info",HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Object> findUser(UUID userId) {
        try{
            UserEntity userEntity = userRepository.findByUserId(userId);
            if(userEntity==null){
                return new ResponseEntity<>("User with UserID: "+userEntity.getUserId()+" doesn't exist",HttpStatus.NOT_FOUND);
            }UserDTO userDTO = UserMapper.userToUserDto(userEntity);
            return new ResponseEntity<>(userDTO,HttpStatus.OK);

        }catch (Exception e){
            System.out.println(e.toString());
            return new ResponseEntity<>("An unexpected error has occurred, please check the logs for more info",HttpStatus.BAD_REQUEST);
        }
    }
    public ResponseEntity<Object> findAll() {
        try{
            List<UserEntity> userEntityList = userRepository.findAll();
            if(userEntityList == null || userEntityList.size()==0)
                return new ResponseEntity<>(Collections.EMPTY_LIST,HttpStatus.OK);
            return new ResponseEntity<>(userEntityList,HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.toString());
            return new ResponseEntity<>("An unexpected error has occurred, please check the logs for more info",HttpStatus.BAD_REQUEST);
        }
    }
    public ResponseEntity<Object>filtrationAndPagination(Integer pageNumber, Integer pageSize, String name, String email, String status){
        Pageable pageable =  PageRequest.of(pageNumber==null? 0: pageNumber, pageSize==null?5:pageSize);
        try{
            Specification<UserEntity> specification = (root, query, criteriaBuilder) ->{
                List<Predicate>predicates = new ArrayList<>();
                if(!StringUtils.isEmpty(name)){
                    predicates.add(criteriaBuilder.equal(root.get("name"),name));
                }
                if(!StringUtils.isEmpty(email)){
                    predicates.add(criteriaBuilder.equal(root.get("email"),email));
                } if(!StringUtils.isEmpty(status)){
                    predicates.add(criteriaBuilder.equal(root.get("status"),status));
                }
                return criteriaBuilder.and(predicates.toArray(predicates.toArray(new Predicate[0])));

            };
            Page<UserEntity> userEntityPage = userRepository.findAll(specification,pageable);
            if(userEntityPage.isEmpty())
                return new ResponseEntity<>(Collections.EMPTY_LIST,HttpStatus.OK);
            return new ResponseEntity<>(userEntityPage,HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.toString());
            return new ResponseEntity<>("An unexpected error has occurred, please check the logs for more info",HttpStatus.BAD_REQUEST);
        }
    }
}