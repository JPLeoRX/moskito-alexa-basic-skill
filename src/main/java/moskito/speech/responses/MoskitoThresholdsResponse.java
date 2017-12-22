package moskito.speech.responses;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.SpeechletResponse;
import moskito.services.AppsURL;
import moskito.services.Responses;
import moskito.services.rest.ThresholdsRest;
import moskito.services.rest.basic_entities.Threshold;
import moskito.speech.AlexaResponses;

import java.util.LinkedList;
import java.util.List;

public interface MoskitoThresholdsResponse {

    default SpeechletResponse getThresholdsResponse(SpeechletRequestEnvelope<IntentRequest> requestEnvelope) {
        // Read the data
        ThresholdsRest thresholdsRest = new ThresholdsRest(AppsURL.current);
        List<Threshold> thresholds = thresholdsRest.getList();


        String cardTitle = Responses.get("ThresholdsResponseTitle");

        String line1 = Responses.get("ThresholdsResponseLine1").replace("${count}", String.valueOf(thresholds.size()));
        List<String> lines = new LinkedList<>();
        for (int i = 0; i < thresholds.size(); i++) {
            Threshold t = thresholds.get(i);
            lines.add(" " + Responses.get("ThresholdsResponseLineN")
                    .replace("${index}", String.valueOf(i + 1))
                    .replace("${thresholdName}", t.getName())
                    .replace("${status}", t.getStatus())
                    .replace("${value}", t.getValue())
            );
        }


        String speechText = line1;
        for (String l : lines)
            speechText += l;

        return AlexaResponses.getTellResponse(cardTitle, speechText, speechText);
    }
}
