package com.joe.rbac.security.utils;

import com.joe.rbac.RbacApplicationTests;
import com.joe.rbac.security.JoeUserDetails;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.List;

import static org.junit.Assert.*;

public class JwtUtilTest extends RbacApplicationTests {
    @Autowired
    private JwtUtil jwtUtil;

    private String token;

    //@Before
    public void testGenerateToken() throws Exception{
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_CUSTOMER");
        JoeUserDetails userDetails = new JoeUserDetails(1, "xiaoming", "123456", authorities);
        token = jwtUtil.generateToken(userDetails);
    }

    @Test
    public void testGetUsernameFromToken() throws Exception{
        String username = jwtUtil.getUsernameFromToken(token);
        assertTrue(username != null);
    }

    @Test
    public void testGetUserByToken(){
//        JoeUserDetails userDetails = jwtUtil.getUserByToken1("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTU2ODIwMTcwNSwidXNlcmlkIjo0LCJjcmVhdGVkIjoxNTY4MTk5OTA1NzU1LCJhdXRob3JpdGllcyI6W3siYXV0aG9yaXR5Ijoic3lzOm1lbnU6dXBkYXRlIn0seyJhdXRob3JpdHkiOiJzeXM6bWVudTpkZWxldGUifSx7ImF1dGhvcml0eSI6InN5czpkZXB0OnVwZGF0ZSJ9LHsiYXV0aG9yaXR5Ijoic3lzOmpvYjphZGQifSx7ImF1dGhvcml0eSI6InN5czp1c2VyOnVwZGF0ZUVtYWlsIn0seyJhdXRob3JpdHkiOiJST0xFXzUifSx7ImF1dGhvcml0eSI6InN5czptZW51OmFkZCJ9LHsiYXV0aG9yaXR5Ijoic3lzOnVzZXI6YWRkIn0seyJhdXRob3JpdHkiOiJzeXM6ZGVwdDpkZWxldGUifSx7ImF1dGhvcml0eSI6InN5czpsb2c6dmlldyJ9LHsiYXV0aG9yaXR5Ijoic3lzOnJvbGU6dmlldyJ9LHsiYXV0aG9yaXR5Ijoic3lzOmpvYjpkZWxldGUifSx7ImF1dGhvcml0eSI6InN5czpsb2c6ZGVsZXRlIn0seyJhdXRob3JpdHkiOiJzeXM6dXNlcjpkZWxldGUifSx7ImF1dGhvcml0eSI6InN5czpkZXB0OnZpZXcifSx7ImF1dGhvcml0eSI6InN5czpqb2I6dmlldyJ9LHsiYXV0aG9yaXR5Ijoic3lzOmRpcHQ6dmlldyJ9LHsiYXV0aG9yaXR5Ijoic3lzOnVzZXI6dXBkYXRlIn0seyJhdXRob3JpdHkiOiJzeXM6bWVudTp2aWV3In0seyJhdXRob3JpdHkiOiJzeXM6cm9sZTphZGQifSx7ImF1dGhvcml0eSI6InN5czp1c2VyOnZpZXcifSx7ImF1dGhvcml0eSI6InN5czp1c2VyOnVwZGF0ZVBhc3MifSx7ImF1dGhvcml0eSI6InN5czpqb2I6dXBkYXRlIn0seyJhdXRob3JpdHkiOiJzeXM6cm9sZTp1cGRhdGUifSx7ImF1dGhvcml0eSI6InN5czpkZXB0OmFkZCJ9LHsiYXV0aG9yaXR5Ijoic3lzOnJvbGU6ZGVsZXRlIn1dfQ.qT_W5Ju5fszmIP5RIXv57k7sWDSet4nsARWpeq_uwqTqsRlGj1CX_CkcNVEjuEmBvWUBfZ2giajujrsTynxgmQ");
//        assertTrue(userDetails != null);
    }

    @Test
    public void testGetAuthorities(){


    }
}