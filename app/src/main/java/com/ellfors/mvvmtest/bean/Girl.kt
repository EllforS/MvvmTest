package com.ellfors.mvvmtest.bean

/**
 * Girl
 * 2020-11-30 11:25
 */
class Girl {
    /**
     * _id : 5e958faf808d6d2fe6b56ecb
     * author : 鸢媛
     * category : Girl
     * createdAt : 2020-04-27 08:00:00
     * desc : 相似的人适合玩闹/互补的人才能终老。 ​​​​
     * images : ["http://gank.io/images/4817628a6762410895f814079a6690a1"]
     * likeCounts : 0
     * publishedAt : 2020-04-27 08:00:00
     * stars : 1
     * title : 第68期
     * type : Girl
     * url : http://gank.io/images/4817628a6762410895f814079a6690a1
     * views : 271
     */
    var _id: String? = null
    var author: String? = null
    var category: String? = null
    var createdAt: String? = null
    var desc: String? = null
    var likeCounts = 0
    var publishedAt: String? = null
    var stars = 0
    var title: String? = null
    var type: String? = null
    var url: String? = null
    var views = 0
    var images: List<String>? = null

    constructor()

    constructor(title: String?, url: String?) {
        this.title = title
        this.url = url
    }
}