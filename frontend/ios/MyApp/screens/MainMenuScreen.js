import React from 'react';
import { View, Text, Button, StyleSheet } from 'react-native';

export default function MainMenuScreen({ navigation }) {
  return (
    <View style={styles.container}>
      <Text style={styles.title}>메인 메뉴</Text>

      <Button
        title="채팅 화면으로 이동"
        onPress={() => navigation.navigate('Chat')}
      />

      <View style={styles.spacing} />
      <Button
        title="로그인 화면으로 이동"
        onPress={() => navigation.navigate('Login')}
      />

      <View style={styles.spacing} />
      <Button
        title="회원가입 화면으로 이동"
        onPress={() => navigation.navigate('Signup')}
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    padding: 24,
  },
  title: {
    fontSize: 24,
    marginBottom: 32,
    fontWeight: 'bold',
  },
  spacing: {
    height: 16,
  },
});
