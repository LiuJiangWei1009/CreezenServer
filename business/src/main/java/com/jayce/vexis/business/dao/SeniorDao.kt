package com.jayce.vexis.business.dao

import com.jayce.vexis.util.dto.PeerDTO

interface SeniorDao {
    fun addAdvice(peerDTO: PeerDTO)
    fun getAdvice(peerDTO: PeerDTO): List<PeerDTO>
}