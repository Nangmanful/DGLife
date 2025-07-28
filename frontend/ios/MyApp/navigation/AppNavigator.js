import React from 'react';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import MainMenuScreen from '../screens/MainMenuScreen';
import ChatScreen from '../screens/ChatScreen';
import LoginScreen from '../screens/LoginScreen';
import SignupScreen from '../screens/SignupScreen';
import NextScreen from '../screens/NextScreen';

const Stack = createNativeStackNavigator();

export default function AppNavigator() {
  return (
    <Stack.Navigator initialRouteName="MainMenu">
      <Stack.Screen name="MainMenu" component={MainMenuScreen} options={{ title: '메인 메뉴' }} />
      <Stack.Screen name="Chat" component={ChatScreen} options={{ title: '채팅' }} />
      <Stack.Screen name="Login" component={LoginScreen} options={{ title: '로그인' }} />
      <Stack.Screen name="Signup" component={SignupScreen} options={{ title: '회원가입' }} />
      <Stack.Screen name="Next" component={NextScreen} options={{ title: '게시판' }} />
    </Stack.Navigator>
  );
}
