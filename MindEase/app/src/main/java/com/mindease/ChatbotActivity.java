package com.mindease;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.mindease.adapter.ChatAdapter;
import com.mindease.model.ChatMessage;
import java.util.ArrayList;
import java.util.List;

public class ChatbotActivity extends AppCompatActivity {
    private EditText userInput;
    private Button sendButton;
    private RecyclerView chatRecyclerView;
    private ChatAdapter chatAdapter;
    private List<ChatMessage> messages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatbot);

        userInput = findViewById(R.id.userInput);
        sendButton = findViewById(R.id.sendButton);
        chatRecyclerView = findViewById(R.id.chatRecyclerView);

        // Setup RecyclerView
        chatAdapter = new ChatAdapter(messages);
        chatRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        chatRecyclerView.setAdapter(chatAdapter);

        // Add welcome message
        messages.add(new ChatMessage("Hello! I'm your MindEase assistant. How are you feeling today?", false));
        chatAdapter.notifyItemInserted(messages.size() - 1);
        chatRecyclerView.scrollToPosition(messages.size() - 1);

        sendButton.setOnClickListener(v -> sendMessage());
    }

    private void sendMessage() {
        String message = userInput.getText().toString().trim();
        if (!message.isEmpty()) {
            // Add user message
            ChatMessage userMessage = new ChatMessage(message, true);
            messages.add(userMessage);
            chatAdapter.notifyItemInserted(messages.size() - 1);
            chatRecyclerView.scrollToPosition(messages.size() - 1);
            userInput.setText("");

            // Simulate AI response (replace with actual API call)
            new android.os.Handler().postDelayed(() -> {
                String response = generateAIResponse(message);
                ChatMessage botMessage = new ChatMessage(response, false);
                messages.add(botMessage);
                chatAdapter.notifyItemInserted(messages.size() - 1);
                chatRecyclerView.scrollToPosition(messages.size() - 1);
            }, 1000);
        }
    }

    private String generateAIResponse(String userMessage) {
        // TODO: Replace with actual AI integration
        if (userMessage.toLowerCase().contains("anxious")) {
            return "I hear you're feeling anxious. Would you like to try a breathing exercise?";
        } else if (userMessage.toLowerCase().contains("sad")) {
            return "I'm sorry you're feeling sad. Remember, it's okay to feel this way. Would you like to talk more about it?";
        } else {
            return "Thank you for sharing. How has this been affecting your day?";
        }
    }
}
