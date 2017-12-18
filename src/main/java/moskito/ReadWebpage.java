package moskito;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.URL;

/**
 * This class will read the Moskito's reply web-page and parse its status
 * We have to use HTML parser, since XML parsers don't like the structure of our document.
 */
public class ReadWebpage {
    public static final String WEBPAGE = "http://burgershop-hamburg.demo.moskito.org/burgershop/moskito-inspect-rest/status";
    public static final String TAG_REPLY = "reply";
    public static final String TAG_SUCCESS = "success";

    public String getSuccess() {
        try {
            // Form a document
            URL url = new URL(WEBPAGE);
            Document document = Jsoup.parse(url, 500);

            // Get reply element
            Element reply = document.getElementsByTag(TAG_REPLY).first();

            // Get success element
            Element success = reply.getElementsByTag(TAG_SUCCESS).first();

            // Return the result
            return success.text();

        }

        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean getSuccessBool() {
        return Boolean.valueOf(this.getSuccess());
    }
}