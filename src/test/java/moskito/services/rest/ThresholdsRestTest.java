package moskito.services.rest;

import moskito.services.AppsURL;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ThresholdsRestTest {

    @Test
    public void getList() {
        Assert.assertEquals(true, new ThresholdsRest(AppsURL.BURGER_SHOP_HAMBURG).getList().size() != 0);
    }
}