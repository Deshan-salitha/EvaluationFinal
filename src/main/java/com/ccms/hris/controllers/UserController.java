package com.ccms.hris.controllers;

import com.ccms.hris.enums.UserStatus;
import com.ccms.hris.models.ResponseWrapper;
import com.ccms.hris.models.dto.UserCreationDto;
import com.ccms.hris.models.dto.UserDto;
import com.ccms.hris.models.entities.Designation;
import com.ccms.hris.models.entities.User;
import com.ccms.hris.services.UserService;
import com.ccms.hris.utils.JwtUtil;
import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("users")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @GetMapping("/hello")
    public String helloTest() {
        return "Hello World";
    }

    @GetMapping("/{id}")
    public ResponseEntity getUserById(@PathVariable Long id) {
        try {
            User user = userService.getUserById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper(user, "success", "fetched"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseWrapper(null, "not found", "No user found"));
        }
    }

    @GetMapping("/")
    public ResponseEntity getAllUsers(
            @RequestParam(name = "pageNo", defaultValue = "1", required = false) int page,
            @RequestParam(name = "pageSize", defaultValue = "10", required = false) int offset
    ) {
        try {
            Page<UserDto> users = userService.getAllUsers(page, offset);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper(users, "success", "fetched"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseWrapper(null, "not found", "No user found"));
        }
    }

    @PostMapping("/")
    public ResponseEntity createUser(@RequestBody UserCreationDto user) {
        try {
            userService.createUser(user);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper(user, "success", "created"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseWrapper(user, "failed", e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@RequestBody UserCreationDto user, @PathVariable Long id) {
        try {

            userService.updateUser(user, id);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper(user, "success", "updated"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseWrapper("failed", "failed", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseWrapper("failed", "failed", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper("", "success", "deleted"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseWrapper("", "failed", e.getMessage()));
        }
    }


    @GetMapping("/filter")
    public ResponseWrapper getByStatusAndId(
            @RequestParam(name = "q", defaultValue = "", required = false) String q,
            @RequestParam(name = "userStatus", defaultValue = "0", required = false) int userStatus,
            @RequestParam(name = "designationName", defaultValue = "", required = false) String designationName,
            @RequestParam(name = "start", defaultValue = "", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(name = "end", defaultValue = "", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate
    ) {
//        Designation designation =
        UserStatus finalUserStatus = UserStatus.INACTIVE;
        if (Objects.equals(userStatus, 0)) {
            finalUserStatus = UserStatus.ACTIVE;
            System.out.println(finalUserStatus
            );
        } else if (Objects.equals(userStatus, 1)) {
            finalUserStatus = UserStatus.INACTIVE;
            System.out.println(finalUserStatus
            );
        } else if (Objects.equals(userStatus, 2)) {
            finalUserStatus = UserStatus.DEACTIVATED;
            System.out.println(finalUserStatus
            );
        } else if (Objects.equals(userStatus, 3)) {
            finalUserStatus = UserStatus.DORMANT;
            System.out.println(finalUserStatus
            );
        }
        try {
//            long designationId = 0;

            List<User> users = userService.filterUsers(q, finalUserStatus, startDate, endDate, designationName);
            return new ResponseWrapper<>(users, "success", "fetched");
        } catch (Exception e) {
            return new ResponseWrapper<>(null, "failed", e.getMessage());
        }
    }

    @GetMapping("/newfilter")
    public ResponseWrapper getByfilters(
            @RequestParam(name = "q", defaultValue = "", required = false) String q,
            @RequestParam(name = "first", defaultValue = "", required = false) String first,
            @RequestParam(name = "second", defaultValue = "", required = false) String second
//            @RequestParam(name = "userStatus",defaultValue = "0", required = false) int userStatus,
//            @RequestParam(name = "designationName", defaultValue = "",required = false) String designationName,
//            @RequestParam(name = "start", defaultValue = "", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
//            @RequestParam(name = "end", defaultValue = "", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate
    ) {
//        Designation designation =

        try {
//            long designationId = 0;

            List<User> users = userService.newFilterUsers(q, first, second);
            return new ResponseWrapper<>(users, "success", "fetched");
        } catch (Exception e) {
            return new ResponseWrapper<>(null, "failed", e.getMessage());
        }
    }


    @PostMapping("/{id}/status")
    public ResponseEntity updateUserStatus(@PathVariable long id,
                                           @RequestParam(name = "userStatus", required = true) UserStatus userStatus

    ) {
        try {
            userService.updateUserStatus(id, userStatus);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper("success", "success", "updated"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseWrapper("failed", "failed", e.getMessage()));
        }
    }


}
