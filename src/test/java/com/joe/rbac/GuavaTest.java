package com.joe.rbac;

import com.google.common.base.Objects;
import org.junit.Test;
import static org.junit.Assert.*;

public class GuavaTest extends RbacApplicationTests {
    @Test
    public void testObjects() throws Exception{
        assertTrue(Objects.equal(null, null));
    }
}
