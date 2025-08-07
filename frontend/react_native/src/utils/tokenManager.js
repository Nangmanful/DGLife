// src/utils/tokenManager.js

import AsyncStorage from '@react-native-async-storage/async-storage';

export const TokenManager = {
  saveToken: async (token, username) => {
    try {
      await AsyncStorage.setItem('jwt_token', token);
      await AsyncStorage.setItem('username', username);
    } catch (e) {
      console.error('Error saving token', e);
    }
  },

  getToken: async () => {
    try {
      return await AsyncStorage.getItem('jwt_token');
    } catch (e) {
      console.error('Error getting token', e);
      return null;
    }
  },

  getUsername: async () => {
    try {
      return await AsyncStorage.getItem('username');
    } catch (e) {
      console.error('Error getting username', e);
      return null;
    }
  },

  clear: async () => {
    try {
      await AsyncStorage.clear();
    } catch (e) {
      console.error('Error clearing token', e);
    }
  },
};
