
import React, { useState } from 'react';
import {
  View,
  Text,
  TextInput,
  Button,
  StyleSheet,
  ToastAndroid,
} from 'react-native';
import HeaderBar from '../components/HeaderBar'; // ✅ 옳은 경로
import { login } from '../api/authApi';
import { TokenManager } from '../utils/tokenManager';

export default function LoginScreen({ navigation }) {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [errorMessage, setErrorMessage] = useState(null);

  const handleLogin = async () => {
    try {
      const response = await login({ username, password });
      const token = response?.data?.token;
      if (token) {
        await TokenManager.saveToken(token, username);
        navigation.reset({ index: 0, routes: [{ name: 'MainMenu' }] });
      } else {
        setErrorMessage('서버 응답 오류: 토큰 없음');
      }
    } catch (err) {
      const msg = '서버 연결 실패 또는 로그인 실패';
      setErrorMessage(msg);
      ToastAndroid.show(msg, ToastAndroid.SHORT);
    }
  };

  return (
    <View style={styles.container}>
      <HeaderBar navigation={navigation} />
      <View style={styles.content}>
        <Text style={styles.title}>로그인</Text>

        <TextInput
          value={username}
          onChangeText={setUsername}
          placeholder="이메일 또는 아이디"
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

        <Button title="로그인" onPress={handleLogin} />
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

