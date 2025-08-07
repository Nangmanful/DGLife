// src/screens/NextScreen.jsx

import React from 'react';
import {
  View,
  Text,
  Image,
  StyleSheet,
  TouchableOpacity,
} from 'react-native';

export default function NextScreen({ navigation }) {
  return (
    <View style={styles.container}>
      {/* Header */}
      <View style={styles.header}>
        <Image source={require('../../assets/menu.png')} style={styles.icon} />

        <TouchableOpacity
          onPress={() => navigation.navigate('MainMenu')}
        >
          <Text style={styles.logo}>DGlife</Text>
        </TouchableOpacity>

        <Image source={require('../../assets/profile_image.png')} style={styles.icon} />
      </View>

      {/* Posts */}
      <View style={styles.posts}>
        <PostItem title="디지스트 최고의 교수님" time="1d" />
        <PostItem title="디지스트 최악의 교수님" time="2d" />
        <PostItem title="디지스트 전공 추천" time="1w" />
      </View>

      {/* Bottom input bar */}
      <View style={styles.inputBar}>
        <Text style={styles.inputText}>새로운 채팅을 입력하세요.</Text>
        <Image source={require('../../assets/mic.png')} style={styles.inputIcon} />
        <Image source={require('../../assets/image.png')} style={styles.inputIcon} />
        <TouchableOpacity
          onPress={() =>
            navigation.navigate('Chat', {
              merge: true,
            })
          }
        >
          <Image source={require('../../assets/group_1.png')} style={styles.inputIcon} />
        </TouchableOpacity>
      </View>
    </View>
  );
}

function PostItem({ title, time }) {
  return (
    <View style={styles.postItem}>
      <Text style={styles.postTitle}>{title}</Text>
      <Text style={styles.postTime}>{time}</Text>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    padding: 16,
  },
  header: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    height: 56,
    marginTop: 8,
  },
  icon: {
    width: 24,
    height: 24,
  },
  logo: {
    fontSize: 24,
    fontWeight: '600',
  },
  posts: {
    marginTop: 32,
    gap: 24,
  },
  postItem: {
    marginBottom: 24,
  },
  postTitle: {
    fontSize: 20,
    fontWeight: 'bold',
    marginBottom: 2,
  },
  postTime: {
    fontSize: 16,
    color: '#666',
  },
  inputBar: {
    position: 'absolute',
    bottom: 16,
    left: 16,
    right: 16,
    backgroundColor: '#fff',
    borderWidth: 1,
    borderColor: '#dfdfdf',
    borderRadius: 8,
    flexDirection: 'row',
    alignItems: 'center',
    paddingHorizontal: 12,
    paddingVertical: 8,
  },
  inputText: {
    flex: 1,
    color: '#828282',
    fontSize: 14,
  },
  inputIcon: {
    width: 24,
    height: 24,
    marginLeft: 8,
  },
});
