package com.padapp.pad;


import com.padapp.pad.config.JwtConfig;
import com.padapp.pad.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootTest

class PadApplicationTests {


    @Autowired
    private JwtConfig jwtConfig;

    @Test
    void contextLoads() {
        System.out.println(jwtConfig.createToken("666"));
    }

    @Test
    public void pared()
    {
        System.out.println(jwtConfig.getTokenClaim(jwtConfig.createToken("666")));
    }
}
