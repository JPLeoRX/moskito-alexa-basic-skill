package moskito.services;

import org.junit.Assert;
import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.*;

public class LocalizationTest {
    @Test
    public void get() {
        Localization a = new Localization("ResponsesBundle");

        a.load(new Locale("en", "US"));
        Assert.assertEquals(true, a.get("Title").equals("MoSKito"));

        a.load(new Locale("ru", "RU"));
        Assert.assertEquals(true, a.get("Title").equals("MoSKito"));

        a.load(new Locale("jp", "JP"));
        Assert.assertEquals(true, a.get("Title").equals("MoSKito"));
    }
}