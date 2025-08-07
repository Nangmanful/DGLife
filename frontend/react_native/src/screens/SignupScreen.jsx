// src/screens/SignupScreen.jsx

import React, { useState } from 'react';
import {
  View,
  Text,
  TextInput,
  Button,
  StyleSheet,
  ToastAndroid,
} from 'react-native';
import HeaderBar from '../components/HeaderBar';
import { signup } from '../api/authApi';

export default function SignupScreen({ navigation }) {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [errorMessage, setErrorMessage] = useState(null);

  const handleSignup = async () => {
    if (!username || !password) {
      setErrorMessage('아이디와 비밀번호를 모두 입력하세요.');
      return;
    }

    try {
      const response = await signup({ username, password });
      if (response.status === 200 || response.status === 201 || response.status === 204) {
        navigation.reset({ index: 0, routes: [{ name: 'Login' }] });
      } else {
        setErrorMessage('이미 존재하는 아이디입니다.');
      }
    } catch (err) {
      const msg = '서버 오류 또는 네트워크 문제';
      setErrorMessage(msg);
      ToastAndroid.show(msg, ToastAndroid.SHORT);
    }
  };

  return (
    <View style={styles.container}>
      <HeaderBar navigation={navigation} />
      <View style={styles.content}>
        <Text style={styles.title}>회원가입</Text>

        <TextInput
          value={username}
          onChangeText={setUsername}
          placeholder="아이디"
          style={styles.input}
        />

        <TextInput
          value={password}
          onChangeText={setPassword}
          placeholder="비밀번호"
          secureTextEntry
          style={styles.input}
        />

        {errorMessage && <Text style={styles.error}>{errorMessage}</Text>}

        <Button title="회원가입" onPress={handleSignup} />
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
  },
  content: {
    flex: 1,
    justifyContent: 'center',
    paddingHorizontal: 24,
  },
  title: {
    fontSize: 24,
    marginBottom: 24,
  },
  input: {
    borderWidth: 1,
    borderColor: '#ccc',
    borderRadius: 4,
    padding: 12,
    marginBottom: 12,
  },
  error: {
    color: 'red',
    marginBottom: 8,
  },
});

