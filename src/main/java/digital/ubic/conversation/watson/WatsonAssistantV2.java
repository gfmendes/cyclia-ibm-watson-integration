package digital.ubic.conversation.watson;

import java.util.HashMap;
import java.util.Map;

import com.ibm.watson.developer_cloud.assistant.v2.Assistant;
import com.ibm.watson.developer_cloud.assistant.v2.model.*;
import com.ibm.watson.developer_cloud.service.security.IamOptions;
import org.apache.commons.lang3.StringUtils;

public class WatsonAssistantV2 {

	public String sendMessage(String message, String sessionId) {

		String assistantId = "6f044e4f-5627-4150-b004-5eee56928852";
		Assistant assistant = getAssistant();

		MessageInputOptions inputOptions = new MessageInputOptions();
		inputOptions.setReturnContext(true);

		MessageInput input = new MessageInput.Builder()
				.messageType("text")
				.text(message)
				.options(inputOptions)
				.build();

		if (StringUtils.isBlank(sessionId)) {
			CreateSessionOptions sessionOptions = new CreateSessionOptions.Builder(assistantId).build();
			SessionResponse sessionResponse = assistant.createSession(sessionOptions).execute();
			sessionId = sessionResponse.getSessionId();
		}

		MessageContext context = getMessageContext();

		MessageOptions options = new MessageOptions.Builder(assistantId, sessionId)
				.context(context)
				.input(input)
				.build();

		MessageResponse response = assistant.message(options).execute();

		return response.toString();

	}

	private MessageContext getMessageContext() {
		MessageContext context = new MessageContext();

		Map<String, String> userDefinedContext = new HashMap<>();
		userDefinedContext.put("account_num","123456");
		Map<String, Map> mainSkillContext = new HashMap<>();
		mainSkillContext.put("user_defined", userDefinedContext);
		MessageContextSkills skillsContext = new MessageContextSkills();
		skillsContext.put("main skill", mainSkillContext);


		MessageContextGlobal global = new MessageContextGlobal();
		MessageContextGlobalSystem globalSystem = new MessageContextGlobalSystem();
		globalSystem.setUserId("123213sdfsdf");
		global.setSystem(globalSystem);

		context.setSkills(skillsContext);
		context.setGlobal(global);
		return context;
	}

	private Assistant getAssistant() {
		String apiKey = "VfWvzSA2Y2EOXtOQ_5nXNR16MEUq7CFDscKXO0xhU1s9";
		String versionDate = "2018-11-08";
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

}
