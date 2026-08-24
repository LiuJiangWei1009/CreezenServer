package com.jayce.vexis.business.dao

import com.jayce.vexis.util.dto.ActiveDTO
import com.jayce.vexis.util.dto.UserDTO

interface UserDao {

    fun findByID(userID: String): UserDTO?

    fun findUserByEmail(email: String): UserDTO?

    fun registerUser(user: UserDTO)

    fun registerActiveData(userID: String)

    fun getAllUser(): List<ActiveDTO>

    fun setAdmin(userId: String): Boolean

    fun deleteUser(userId: String): Boolean

    fun queryRelation(userId: String, fansId: String): Int

    fun followUser(userId: String, fansId: String, relation: Int): Boolean
    
    fun updateRelation(userId: String): Boolean

    fun queryByContent(content: String): List<UserDTO>
}