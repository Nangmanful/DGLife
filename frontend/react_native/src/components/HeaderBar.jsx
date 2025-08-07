// src/components/HeaderBar.jsx

import React, { useEffect, useState } from 'react';
import {
  View,
  Text,
  Image,
  TouchableOpacity,
  StyleSheet,
} from 'react-native';
import { TokenManager } from '../utils/tokenManager';

export default function HeaderBar({ navigation, onMenuClick, showProfileIcon = true }) {
  const [username, setUsername] = useState(null);

  useEffect(() => {
    const loadUsername = async () => {
      try {
        const name = await TokenManager.getUsername();
        if (typeof name === 'string') {
          setUsername(name);
        } else {
          setUsername(null);
        }
      } catch (e) {
        console.warn('Failed to load username:', e);
        setUsername(null);
      }
    };
    loadUsername();
  }, []);

  return (
    <View style={styles.container}>
      <TouchableOpacity onPress={onMenuClick}>
        <Image
          source={require('../../assets/menu.png')}
          style={styles.icon}
          onError={() => console.warn('menu.png not found')}
        />
      </TouchableOpacity>

      <TouchableOpacity
        onPress={() => {
          navigation.navigate('MainMenu');
        }}
      >
        <Text style={styles.logoText}>DGlife</Text>
      </TouchableOpacity>

      {showProfileIcon ? (
        <View style={styles.profileContainer}>
          <Image
            source={require('../../assets/profile_image.png')}
            style={styles.icon}
            onError={() => console.warn('profile_image.png not found')}
          />
          <Text style={styles.usernameText}>
            {username ? `${username}님` : '비로그인'}
          </Text>
        </View>
      ) : (
        <View style={{ width: 24 }} />
      )}
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    height: 56,
    paddingHorizontal: 16,
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'space-between',
    backgroundColor: '#fff',
  },
  icon: {
    width: 24,
    height: 24,
  },
  logoText: {
    fontSize: 24,
    fontWeight: '600',
    color: '#000',
  },
  profileContainer: {
    flexDirection: 'row',
    alignItems: 'center',
  },
  usernameText: {
    fontSize: 14,
    color: 'gray',
    marginLeft: 4,
  },
});
