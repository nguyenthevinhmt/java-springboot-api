package rest_api_app.Common.Exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class LocalizeService {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Map<String, Map<String, String>> messagesCache = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        // Có thể thêm các ngôn ngữ khác vào đây
        loadMessages("en");
        loadMessages("vi");
    }

    private void loadMessages(String language) {
        try {
            ClassPathResource resource = new ClassPathResource("localizations/" + language + ".json");
            Map<String, String> messages = objectMapper.readValue(resource.getInputStream(),
                    new com.fasterxml.jackson.core.type.TypeReference<HashMap<String, String>>() {});
            messagesCache.put(language, messages);
        } catch (IOException e) {
            e.printStackTrace();
            // Khởi tạo map rỗng nếu không đọc được file
            messagesCache.put(language, new HashMap<>());
        }
    }

    public String getMessage(int errorCode, String language) {
        String errorName = ErrorCode.getErrorName(errorCode);
        return getMessage(errorName, language);
    }

    public String getMessage(String errorName, String language) {
        // Kiểm tra ngôn ngữ có tồn tại không, nếu không dùng tiếng Anh làm fallback
        Map<String, String> messages = messagesCache.getOrDefault(language, messagesCache.getOrDefault("en", new HashMap<>()));

        // Lấy message theo errorName, nếu không có thì trả về "Unknown error"
        return messages.getOrDefault(errorName, "Unknown error");
    }
}
