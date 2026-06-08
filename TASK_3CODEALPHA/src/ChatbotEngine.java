public class ChatbotEngine {

    private KnowledgeBase kb;

    public ChatbotEngine() {

        kb = new KnowledgeBase();
    }

    public String getResponse(String input) {

        input = NLPProcessor.preprocess(input);

        String answer = kb.search(input);

        if(answer != null)
            return answer;

        if(input.contains("hello") || input.contains("hi"))
            return "Hello! How can I help you today?";

        if(input.contains("how are you"))
            return "I am doing great. Thanks for asking.";

        if(input.contains("bye"))
            return "Goodbye! Have a nice day.";

        return "Sorry, I don't know the answer to that question.";
    }
}