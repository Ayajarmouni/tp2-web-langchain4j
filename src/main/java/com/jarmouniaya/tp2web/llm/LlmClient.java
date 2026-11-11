package com.jarmouniaya.tp2web.llm;

import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.google.genai.GoogleGenAiChatModel;
import dev.langchain4j.service.AiServices;
import jakarta.enterprise.context.Dependent;

import java.io.Serializable;

/**
 * Classe "métier" liée au LLM (Gemini), qui utilise LangChain4j.
 * Dependent : l'instance disparaît avec le backing bean (portée view) qui l'injecte.
 */
@Dependent
public class LlmClient implements Serializable {

    /** Rôle système choisi par l'utilisateur. */
    private String systemRole;
    private Assistant assistant;
    /** Historique de la conversation, géré par LangChain4j. */
    private ChatMemory chatMemory;

    public LlmClient() {
        String cle = System.getenv("GEMINI_KEY");
        if (cle == null || cle.isBlank()) {
            throw new IllegalStateException("Variable d'environnement GEMINI_KEY absente");
        }

        ChatModel model = GoogleGenAiChatModel.builder()
                .apiKey(cle)
                .modelName("gemini-3.5-flash-lite")   // le même nom de modèle que dans tes tests
                .temperature(0.7)
                .build();

        this.chatMemory = MessageWindowChatMemory.withMaxMessages(20);
        this.assistant = AiServices.builder(Assistant.class)
                .chatModel(model)
                .chatMemory(chatMemory)
                .build();
    }

    /**
     * Définit le rôle système : on vide la mémoire (le contexte change), puis on ajoute
     * le rôle à la mémoire sous forme de SystemMessage pour que le LLM en tienne compte.
     */
    public void setSystemRole(String role) {
        this.systemRole = role;
        chatMemory.clear();
        chatMemory.add(SystemMessage.from(role));
    }

    /** Envoie une question au LLM et retourne sa réponse. */
    public String envoyerRequete(String prompt) {
        return assistant.chat(prompt);
    }
}