package com.steis.manager.service;

import com.steis.manager.domain.Role;
import com.steis.manager.domain.User;
import com.steis.manager.repository.UserRepo;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepo userRepo;


    @Test
    void saveUser() {

        User user = new User();

        boolean saveUser = userService.saveUser(user);

        Assert.assertTrue(saveUser);
        Assert.assertTrue(CoreMatchers.is(user.getRoles()).matches(Collections.singleton(Role.USER)));
        Assert.assertFalse(CoreMatchers.is(user.getRoles()).matches(Role.ADMIN));
        Assert.assertFalse(CoreMatchers.is(user.getRoles()).matches(Role.MASTER));

    }

    @Test
    void failedSaveUser() {

        Mockito.doReturn(new User())
                .when(userRepo)
                .findUserByUsername("John");

        User user = new User();
        user.setUsername("John");

        Assert.assertFalse(userService.saveUser(user));

    }

    @Test
    void findAll() {

        userRepo.save(new User());
        Assert.assertNotNull(userRepo.findAll());
        Mockito.verify(userRepo,Mockito.times(1)).findAll();

    }

    @Test
    void correctUser() {
        User user = new User();

        user.setRoles(new HashSet<>());
        user.getRoles().add(Role.MASTER);

        Map<String,String> form = new HashMap<>();
        form.put("some", "some");
        form.put(Role.USER.toString(),"");

        userService.correctUser("John",user,form);

        Mockito.verify(userRepo,Mockito.times(1)).save(user);
    }
}