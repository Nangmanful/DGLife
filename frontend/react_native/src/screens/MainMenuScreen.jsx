// src/screens/MainMenuScreen.jsx

import React from 'react';
import {
  View,
  Text,
  Button,
  StyleSheet,
} from 'react-native';
import HeaderBar from '../components/HeaderBar';

export default function MainMenuScreen({ navigation }) {
  return (
    <View style={styles.container}>
      <HeaderBar navigation={navigation} />

      <View style={styles.content}>
        <Text style={styles.title}>메인 메뉴</Text>

        <Button
          title="채팅 화면으로 이동"
          onPress={() => navigation.navigate('Chat')}
        />

        <View style={styles.spacer} />

        <Button
          title="로그인 화면으로 이동"
          onPress={() => navigation.navigate('Login')}
        />

        <View style={styles.spacer} />

        <Button
          title="회원가입 화면으로 이동"
          onPress={() => navigation.navigate('Signup')}
        />
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
    marginBottom: 32,
    textAlign: 'center',
  },
  spacer: {
    height: 16,
  },
});
