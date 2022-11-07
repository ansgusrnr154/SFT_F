package com.example.sft_p.Fragment.ListFragment

data class ContentDTO(var explain : String? = null,
                      var imageUrl : String? = null,
                      var uid : String? = null,
                      var userId : String? = null,
                      var title : String? = null,
                      var menu : String? = null,
                      var locate : String? = null,
                      var timestamp: Long? = null,
                      var favoritesCount : Int = 0, var favorites : Map<String, Boolean> = HashMap()){
    data class Comment(var uid: String? = null,
                       var userId: String? = null,
                       var comment: String? = null,
                       var timestamp : Long? = null)
}