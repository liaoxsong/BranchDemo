package co.songliao.branchdemo.entity

/**
 * created by Liao Song on 6/28/21
 */
data class Animal(var name: String, var url :String ) {

    companion object {
        val getMockAnimals = listOf<Animal>(
            Animal("cute cat", "https://img2.doubanio.com/icon/ul6609719-11.jpg"),
            Animal("Golden retrieval pub", "https://img2.doubanio.com/view/group_topic/l/public/p464306923.webp"),
            Animal("Samoyed", "https://img9.doubanio.com/view/group_topic/l/public/p463181534.webp")
        )
    }
}