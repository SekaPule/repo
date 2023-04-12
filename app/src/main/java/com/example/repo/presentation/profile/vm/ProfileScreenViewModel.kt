package com.example.repo.presentation.profile.vm

import androidx.lifecycle.ViewModel
import com.example.repo.domain.model.Friend

class ProfileScreenViewModel : ViewModel() {

    fun getFriends(): List<Friend> = listOf(
        Friend(
            id = 1,
            icon = "https://cdn.dribbble.com/users/130163/screenshots/6209150/twitch-avatar.png",
            fullName = "Дмитрий Валерьевич"
        ),
        Friend(
            id = 2,
            icon = "https://coolsen.ru/wp-content/uploads/2022/02/156-20220208_183149.jpg",
            fullName = "Евгений Александров"
        ),
        Friend(
            id = 3,
            icon = "https://ru-static.z-dn.net/files/dec/766e9ce4da138cddd8003fe88f8dbf8e.png11",
            fullName = "Виктор Кузнецов"
        ),
    )
}