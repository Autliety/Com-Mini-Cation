package pers.auly.cmcwip;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class AbstractTests {
    
    @Before
    public void init() {
        System.out.println("-----Start Test: " + this.getClass().getName() + "-----");
    }
    
    @After
    public void after() {
        System.out.println("-----Ended Test: " + this.getClass().getName() + "-----");
    }
    
}
