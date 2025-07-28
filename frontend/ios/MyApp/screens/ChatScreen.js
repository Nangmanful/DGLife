import React, { useState } from 'react';
import {
  View,
  Text,
  TextInput,
  Button,
  ScrollView,
  StyleSheet,
  TouchableOpacity,
  KeyboardAvoidingView,
  Platform,
} from 'react-native';
import axios from 'axios';

export default function ChatScreen() {
  const [messages, setMessages] = useState([]);
  const [inputText, setInputText] = useState('');

  const sendMessage = async () => {
    if (!inputText.trim()) return;
    const userMessage = { content: inputText, isUser: true };
    setMessages((prev) => [...prev, userMessage]);
    setInputText('');

    try {
      const res = await axios.post('https://your-api-url.com/chat', {
        userId: 'anonymous',
        message: userMessage.content,
      });
      const reply = res.data?.reply || '응답 오류';
      setMessages((prev) => [...prev, { content: reply, isUser: false }]);
    } catch (err) {
      setMessages((prev) => [
        ...prev,
        { content: `❗ 서버 오류: ${err.message}`, isUser: false },
      ]);
    }
  };

  return (
    <KeyboardAvoidingView
      style={styles.container}
      behavior={Platform.OS === 'ios' ? 'padding' : undefined}
    >
      <ScrollView style={styles.chatArea} contentContainerStyle={{ paddingBottom: 80 }}>
        {messages.map((msg, index) => (
          <View
            key={index}
            style={{
              alignItems: msg.isUser ? 'flex-end' : 'flex-start',
              marginBottom: 8,
            }}
          >
            <View
              style={[styles.messageBubble, { backgroundColor: msg.isUser ? '#000' : '#eee' }]}
            >
              <Text style={{ color: msg.isUser ? '#fff' : '#000' }}>{msg.content}</Text>
            </View>
          </View>
        ))}
      </ScrollView>

      <View style={styles.inputRow}>
        <TextInput
          style={styles.input}
          value={inputText}
          onChangeText={setInputText}
          placeholder="메시지를 입력하세요"
        />
        <TouchableOpacity onPress={sendMessage} style={styles.sendButton}>
          <Text style={{ color: '#fff' }}>전송</Text>
        </TouchableOpacity>
      </View>
    </KeyboardAvoidingView>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 16,
  },
  chatArea: {
    flex: 1,
  },
  messageBubble: {
    padding: 10,
    borderRadius: 16,
    maxWidth: '80%',
  },
  inputRow: {
    position: 'absolute',
    bottom: 0,
    left: 0,
    right: 0,
    flexDirection: 'row',
    padding: 8,
    backgroundColor: '#fff',
    borderTopWidth: 1,
    borderColor: '#ddd',
  },
  input: {
    flex: 1,
    borderWidth: 1,
    borderColor: '#ccc',
    borderRadius: 8,
    paddingHorizontal: 12,
    marginRight: 8,
  },
  sendButton: {
    backgroundColor: '#000',
    paddingHorizontal: 16,
    paddingVertical: 10,
    borderRadius: 8,
  },
});
