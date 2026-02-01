import dev.langchain4j.model.anthropic.AnthropicChatModel;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.request.ChatRequest;
import dev.langchain4j.model.chat.request.ResponseFormat;
import dev.langchain4j.model.chat.request.ResponseFormatType;
import dev.langchain4j.model.chat.request.json.JsonObjectSchema;
import dev.langchain4j.model.chat.request.json.JsonSchema;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.data.message.UserMessage;

public class Main {
    public static void main(String[] args) {
        ChatModel model = AnthropicChatModel.builder()
                .apiKey(System.getenv("ANTHROPIC_API_KEY"))
                .modelName("claude-haiku-4-5-20251001")
                .build();

        JsonObjectSchema schema = JsonObjectSchema.builder()
                .addStringProperty("capital")
                .addStringProperty("country")
                .addIntegerProperty("population")
                .required("capital", "country", "population")
                .build();

        ResponseFormat responseFormat = ResponseFormat.builder()
                .type(ResponseFormatType.JSON)
                .jsonSchema(JsonSchema.builder()
                        .name("CapitalInfo")
                        .rootElement(schema)
                        .build())
                .build();

        ChatRequest request = ChatRequest.builder()
                .messages(UserMessage.from("What is the capital of France?"))
                .responseFormat(responseFormat)
                .build();

        ChatResponse response = model.chat(request);
        System.out.println(response.aiMessage().text());
    }
}
