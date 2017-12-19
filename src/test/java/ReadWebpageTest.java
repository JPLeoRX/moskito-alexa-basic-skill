import moskito.services.ReadWebpage;
import org.junit.Assert;
import org.junit.Test;

public class ReadWebpageTest {

    @Test
    public void getSuccess() {
        ReadWebpage a = new ReadWebpage();
        Assert.assertEquals("true", a.getSuccess());
    }

    @Test
    public void getSuccessBool() {
        ReadWebpage a = new ReadWebpage();
        Assert.assertEquals(true, a.getSuccessBool());
    }
}