package com.joe.rbac;

import com.google.common.base.Objects;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.Assert.*;

public class GuavaTest extends RbacApplicationTests {
    @Test
    public void testObjects() throws Exception{
        assertTrue(Objects.equal(null, null));
    }

    @Test
    public void testBCryptPasswordEncoder(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = encoder.encode("123456");
        System.out.println(password);
    }

}
