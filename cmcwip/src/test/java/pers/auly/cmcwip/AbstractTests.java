package pers.auly.cmcwip;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class AbstractTests {
    
    @BeforeClass
    public static void init() {
        System.out.println("-----Start Test-----");
    }
    
    @AfterClass
    public static void after() {
        System.out.println("-----Ended Test-----");
    }
    
}
