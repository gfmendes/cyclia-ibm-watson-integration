package digital.ubic.conversation.filtering.model;


import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FilteringContainsTest {

    @Test
    public void when_multiple_calculations() {

        FilteringContains contains = new FilteringContains("teste", "40");
        Double calc = contains.calculate("abc,dce,1,2,ttre", "abc");
        Assert.assertEquals(calc, 40D, 0);
        calc = contains.calculate("abc,dce,1,2,ttre", "ttre");
        Assert.assertEquals(calc, 40D, 0);
        calc = contains.calculate("abc,dce,1,2,ttre", "1");
        Assert.assertEquals(calc, 40D, 0);
        calc = contains.calculate("abc,dce,1,2,ttre", "notpresent");
        Assert.assertEquals(calc, 0D, 0);
    }

    @Test
    public void when_no_product_values() {

        FilteringContains contains = new FilteringContains("teste", "40");
        Double calc = contains.calculate("", "abc");
        Assert.assertEquals(calc, 0D, 0);
    }

}
