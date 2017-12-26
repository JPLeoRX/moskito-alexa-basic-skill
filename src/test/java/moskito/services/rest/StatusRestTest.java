package moskito.services.rest;

import moskito.services.AppsURL;
import org.junit.Assert;
import org.junit.Test;

public class StatusRestTest {

    @Test
    public void getStatus() {
        Assert.assertEquals("green", new StatusRest(AppsURL.BURGER_SHOP_HAMBURG).getStatusString());
    }
}