package digital.ubic.conversation.watson;

import com.google.gson.Gson;
import com.ibm.watson.developer_cloud.assistant.v1.Assistant;
import com.ibm.watson.developer_cloud.assistant.v1.model.Context;
import com.ibm.watson.developer_cloud.assistant.v1.model.InputData;
import com.ibm.watson.developer_cloud.assistant.v1.model.MessageOptions;
import com.ibm.watson.developer_cloud.assistant.v1.model.MessageResponse;
import com.ibm.watson.developer_cloud.service.security.IamOptions;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class WatsonAssistantV1 {

	public String sendMessage(String message, String context) {

		String workspaceId = "aba996fb-c284-4d7e-8113-a6819a81d778";
		//String workspaceId = "9f7c2180-548f-47d3-8c95-c2d1615b2492";
		Assistant assistant = getAssistant();

		InputData input = new InputData.Builder(message).build();

		MessageOptions options = new MessageOptions.Builder(workspaceId)
				.input(input)
				.context(parseContext(context))
				.build();

		MessageResponse response = assistant.message(options).execute();

		return response.toString();
	}

	private Assistant getAssistant() {
		String apiKey = "6PryJC8fWZNC4gUBTMiqNrrTmPkOoDnf_g199h6MUUuL";
		//String apiKey = "hweJqyz1nrd382BQQI-2gG9kJTYxUWb0NdAB7k4z77RQ";
		String versionDate = "2018-09-20";
		String endPoint = "https://gateway.watsonplatform.net/assistant/api";

		IamOptions iamoptions = new IamOptions.Builder().apiKey(apiKey).build();
		Assistant assistant = new Assistant(versionDate, iamoptions);
		assistant.setEndPoint(endPoint);
		assistant.setDefaultHeaders(getDefaultHeaders());
		return assistant;
	}

	private Map<String, String> getDefaultHeaders() {
		Map<String, String> headers = new HashMap<>();
		headers.put("X-Watson-Metadata", "customer_id=1234");
		return headers;
	}

	private Context parseContext(String context) {
        Gson gson = new Gson();
        Object contextObject = gson.fromJson(context, Context.class);
        return (Context) contextObject;
	}
}