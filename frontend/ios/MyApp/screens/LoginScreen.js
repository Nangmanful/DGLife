import React, { useState } from 'react';
import { View, Text, TextInput, Button, StyleSheet, Alert } from 'react-native';
import axios from 'axios';

export default function LoginScreen({ navigation }) {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState(null);

  const handleLogin = async () => {
    if (!username || !password) {
      setError('아이디와 비밀번호를 입력해주세요.');
      return;
    }

    try {
      const response = await axios.post('https://your-api-url.com/login', {
        username,
        password,
      });

      const token = response.data?.token;
      if (token) {
        // 예시: 토큰을 저장하는 부분 (AsyncStorage 사용 가능)
        Alert.alert('로그인 성공', `Token: ${token}`);
        navigation.navigate('MainMenu');
      } else {
        setError('토큰이 존재하지 않습니다.');
      }
    } catch (err) {
      setError('로그인 실패: 아이디 또는 비밀번호가 잘못되었습니다.');
    }
  };

  return (
    <View style={styles.container}>
      <Text style={styles.title}>로그인</Text>
      <TextInput
        style={styles.input}
        placeholder="아이디"
        value={username}
        onChangeText={setUsername}
      />
      <TextInput
        style={styles.input}
        placeholder="비밀번호"
        value={password}
        onChangeText={setPassword}
        secureTextEntry
      />
      {error && <Text style={styles.error}>{error}</Text>}
      <Button title="로그인" onPress={handleLogin} />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    paddingHorizontal: 24,
  },
  title: {
    fontSize: 24,
    fontWeight: 'bold',
    marginBottom: 24,
    alignSelf: 'center',
  },
  input: {
    borderWidth: 1,
    borderColor: '#ccc',
    borderRadius: 8,
    padding: 12,
    marginBottom: 16,
  },
  error: {
    color: 'red',
    marginBottom: 16,
  },
});
