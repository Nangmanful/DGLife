// src/screens/ChatScreen.jsx

import React, { useState } from 'react';
import {
  View,
  Text,
  TextInput,
  ScrollView,
  TouchableOpacity,
  Image,
  StyleSheet,
} from 'react-native';
import HeaderBar from '../components/HeaderBar';
import { sendMessage } from '../api/chatApi';
import { TokenManager } from '../utils/tokenManager';

export default function ChatScreen({ navigation }) {
  const [messages, setMessages] = useState([]);
  const [inputText, setInputText] = useState('');

  const handleSend = async () => {
    if (!inputText.trim()) return;

    const userMessage = inputText;
    setMessages([...messages, { content: userMessage, isUser: true }]);
    setInputText('');

    const userId = await TokenManager.getUsername() || 'anonymous';

    try {
      const response = await sendMessage({ userId, message: userMessage });
      const reply = response?.data?.reply || '응답 오류';
      setMessages((prev) => [...prev, { content: reply, isUser: false }]);
    } catch (error) {
      setMessages((prev) => [
        ...prev,
        { content: `❗ 서버 연결 실패: ${error.message}`, isUser: false },
      ]);
    }
  };

  return (
    <View style={styles.container}>
      <HeaderBar navigation={navigation} onMenuClick={() => navigation.navigate('Next')} />

      <ScrollView contentContainerStyle={styles.scrollContent}>
        {messages.map((msg, idx) => (
          <View
            key={idx}
            style={[
              styles.messageRow,
              { justifyContent: msg.isUser ? 'flex-end' : 'flex-start' },
            ]}
          >
            {!msg.isUser && (
              <Image source={require('../../assets/image_2.png')} style={styles.icon} />
            )}
            <View
              style={[
                styles.messageBubble,
                { backgroundColor: msg.isUser ? 'black' : '#f5f5f5' },
              ]}
            >
              <Text style={{ color: msg.isUser ? 'white' : 'black' }}>
                {msg.content}
              </Text>
            </View>
          </View>
        ))}
      </ScrollView>

      <View style={styles.inputBar}>
        <TextInput
          value={inputText}
          onChangeText={setInputText}
          placeholder="채팅을 입력하세요."
          style={styles.input}
        />
        <TouchableOpacity onPress={handleSend}>
          <Image
            source={require('../../assets/group_1.png')}
            style={styles.sendIcon}
          />
        </TouchableOpacity>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
  },
  scrollContent: {
    paddingHorizontal: 12,
    paddingVertical: 8,
    paddingBottom: 60,
  },
  messageRow: {
    flexDirection: 'row',
    marginVertical: 4,
    alignItems: 'flex-end',
  },
  icon: {
    width: 24,
    height: 24,
    marginRight: 6,
  },
  messageBubble: {
    borderRadius: 18,
    paddingVertical: 8,
    paddingHorizontal: 16,
    maxWidth: '80%',
  },
  inputBar: {
    position: 'absolute',
    bottom: 0,
    left: 0,
    right: 0,
    padding: 12,
    flexDirection: 'row',
    backgroundColor: '#fff',
    borderTopWidth: 1,
    borderTopColor: '#ddd',
    alignItems: 'center',
  },
  input: {
    flex: 1,
    paddingVertical: 8,
    paddingHorizontal: 12,
    borderWidth: 1,
    borderColor: '#ddd',
    borderRadius: 8,
    marginRight: 8,
    backgroundColor: '#fff',
  },
  sendIcon: {
    width: 24,
    height: 24,
  },
});
