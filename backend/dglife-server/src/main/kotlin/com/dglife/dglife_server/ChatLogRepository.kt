package com.dglife.dglife_server

import org.springframework.data.jpa.repository.JpaRepository

interface ChatLogRepository : JpaRepository<ChatLog, Long>
