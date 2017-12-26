package moskito.speech.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HintRandomizer {
    public static List<String> keys = new ArrayList<>();
    static {
        keys.add("HelpMessageHint1");
        keys.add("HelpMessageHint2");
        keys.add("HelpMessageHint3");
    }

    public static Random random = new Random();

    public static String getHintKey() {
        return keys.get(random.nextInt(keys.size()));
    }
}
