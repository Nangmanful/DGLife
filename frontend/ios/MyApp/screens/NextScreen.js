import React from 'react';
import {
  View,
  Text,
  StyleSheet,
  TextInput,
  TouchableOpacity,
  ScrollView,
} from 'react-native';

export default function NextScreen({ navigation }) {
  return (
    <View style={styles.container}>
      {/* 상단 헤더 */}
      <View style={styles.header}>
        <Text style={styles.title}>DGlife</Text>
      </View>

      {/* 게시물 리스트 */}
      <ScrollView style={styles.postList}>
        <PostItem title="디지스트 최고의 교수님" time="1일 전" />
        <PostItem title="디지스트 최악의 교수님" time="2일 전" />
        <PostItem title="디지스트 전공 추천" time="1주 전" />
      </ScrollView>

      {/* 하단 입력창 */}
      <View style={styles.inputBar}>
        <TextInput
          style={styles.input}
          placeholder="새로운 채팅을 입력하세요."
        />
        <TouchableOpacity
          style={styles.icon}
          onPress={() => navigation.navigate('Chat')}
        >
          <Text style={{ fontWeight: 'bold' }}>📨</Text>
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
    paddingVertical: 12,
    alignItems: 'center',
  },
  title: {
    fontSize: 24,
    fontWeight: 'bold',
  },
  postList: {
    flex: 1,
    marginTop: 16,
  },
  postItem: {
    marginBottom: 24,
  },
  postTitle: {
    fontSize: 18,
    fontWeight: '600',
    marginBottom: 4,
  },
  postTime: {
    fontSize: 14,
    color: '#666',
  },
  inputBar: {
    flexDirection: 'row',
    alignItems: 'center',
    borderTopWidth: 1,
    borderColor: '#ddd',
    paddingTop: 8,
    paddingBottom: 16,
  },
  input: {
    flex: 1,
    borderWidth: 1,
    borderColor: '#ccc',
    borderRadius: 8,
    padding: 10,
    marginRight: 8,
  },
  icon: {
    padding: 10,
  },
});
