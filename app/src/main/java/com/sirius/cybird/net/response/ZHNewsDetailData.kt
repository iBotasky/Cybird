package com.sirius.cybird.net.response

import com.google.gson.annotations.SerializedName

data class ZHNewsDetailData(

        /**
         * {
        "body": "<div class=\"main-wrap content-wrap\">\n<div class=\"headline\">\n\n<div class=\"img-place-holder\"></div>\n\n\n\n</div>\n\n<div class=\"content-inner\">\n\n\n\n\n<div class=\"question\">\n<h2 class=\"question-title\">为什么很多公司都不招大龄码农？</h2>\n\n<div class=\"answer\">\n\n<div class=\"meta\">\n<img class=\"avatar\" src=\"http://pic3.zhimg.com/v2-0ada9aeffba67c992041876dc08575ae_is.jpg\">\n<span class=\"author\">100offer，</span><span class=\"bio\">让最好的人才遇见更好的机会</span>\n</div>\n\n<div class=\"content\">\n<p>其实这个问题应该先问是不是，再问为什么。</p>\r\n<p>先回答是不是的问题，是，大龄程序员找纯技术方向的工作挺难的，但并不是没有。</p>\r\n<p>现有的回答中大部分都在变着花样，婉转地告诉你原因：因为大龄码农的<strong>性价比低</strong>。 大龄码农和小鲜肉码农比性价比肯定是没有意义的，也不是我们回答讨论的重点。</p>\r\n<p>我们从目标倒推，不难理解背后公司的本质，公司认为我们给了你 P7 及以上的评级，公司希望你能够提供更多的价值。的确，纯技术的进步也是这种价值的一种体现；但但凡经历过晋升的工程师都知道，在大部分以业务为导向的公司里，你从 P7 及以上往上升他们更看重的是你能够为业务提供什么样<strong>显著的增量价值</strong>。</p>\r\n<p>说白了就是，32+ 岁以上的程序员，如果不能在 title 上有所进步，比如从资深程序员进阶到架构师，那么市场会直白的告诉你，你想要追逐更高薪的工作很难。</p>\r\n<p>说到这里，基本回答了问题：为什么很多公司都不招大龄码农。</p>\r\n<p>回到开头，我们有提到，这些工作并不是没有，那么这些工作都给了什么样的「大龄码农」呢？</p>\r\n<hr><p><strong>追求技术导向的大龄码农有哪些选择？</strong></p>\r\n<p>在我们接触到候选人中，主要诉求是寻找技术导向公司、GEEK 一点的岗位，并且成功入职的，工作 5 年及以上的码农中，大部分有两种选择：</p>\r\n<p>1）降薪、平薪跳槽加入下一家公司；</p>\r\n<p>2）进阶成为架构师、首席架构师。</p>\r\n<blockquote>在这个回答的前提条件下，我们仅探讨不综合转型的技术人求职方向。想看综合的数据，可以参考我们以前的回答：<a href=\"https://www.zhihu.com/question/63843461/answer/297834759\">100offer：三十岁还没有走到管理岗的人，后来都做了什么？</a>；<a href=\"https://www.zhihu.com/question/264010609/answer/289125151\">100offer：大龄程序员都去哪了？</a></blockquote>\r\n<ul><li><strong>大部分降薪、平薪跳槽加入下一家公司</strong></li>\r\n</ul><p>非常不幸，如果你工作 6 年及以上，还想在市场上看一些不需要技术管理的岗位，那么可能只能看一些 P6+ 至 P7 的岗位了。</p>\r\n<p>我们曾经服务过多位候选人在 32 岁仍然以 P6 级别进入某 BAT，降薪 3 万多（跳槽前年薪 40 万）多加入。</p>\r\n<p><img class=\"content-image\" src=\"http://pic3.zhimg.com/70/v2-ebe2a92f2b1eea6ca2e84bdb6c864746_b.jpg\" alt=\"\"></p>\r\n<p>还有的工作 6+ 年，硕士毕业并且有良好大厂背景的候选人因为不能接受新的岗位给出的 offer，留在了过去的公司。</p>\r\n<p>这背后的矛盾很简单，就是<strong>公司期望和个人技能点愿望的基本矛盾</strong>。</p>\r\n<p>从 18 年 Q1 季度的市场需求来看，凡是工作 5 年以上的候选人，市场倾向于 offer 一些能力可以到架构师和技术经理的候选人。</p>\r\n<p><img class=\"content-image\" src=\"http://pic2.zhimg.com/70/v2-11fe2f25ee51a4e3b1f5621e60f60b7d_b.jpg\" alt=\"\"></p>\r\n<blockquote>一些例外： 但这类情况中也有例外，比如最近风口上的算法岗位等，因为互联网背景的候选人并不足以支撑市场的需求等原因，有很多传统 IT 行业的 30 岁 + 候选人拿到了薪资翻番的工程师 offer。</blockquote>\r\n<ul><li><strong>进阶成为架构师、首席架构师</strong></li>\r\n</ul><p>如果说上一种选择有点「凄风苦雨」，那么这种选择的候选人可以称得上是香馍馍了。</p>\r\n<p>已经工作 8 年的博士候选人 X 先生，就是这么一个香馍馍。09 年博士毕业的他，毕业后先进入了世界知名软件公司，工作 5 年后跳到某一互联网公司担任架构师。从事智能客服等业务的开发工作，主要负责平台架构设计、核心算法优化等工作。17 年出来看机会时，明确向他的人才顾问 Osborn 提出想要接着看一些 GEEK 一些的工作岗位。最后拒绝了某一 BAT 大厂的 offer，加入了一家技术驱动的创业公司，年薪从 50 万涨到了 80 万，并且公司还追加了可观的 RSU。</p>\r\n<p>除了 X 先生之外，已经 40 岁的前端技术总监 Y 先生也有同样的诉求。他连续 2 年在平台上进行展示，但一直没有足够打动他的机会。根据他的需求，他的人才顾问 Osborn 给他推荐了一些猎头职位，最后成功入职某数据公司。</p>\r\n<p><img class=\"content-image\" src=\"http://pic1.zhimg.com/70/v2-39227f637b292386534b72abb9bb4ee4_b.jpg\" alt=\"\"></p>\r\n<p>这样的例子在 100offer 的高端猎头部中不断上演，对于这些工作 5+ 年的候选人，找一个合适的机会的确不容易， Y 先生一次跳槽等了 1 年多。但是往往机会来了，他们就会很迅速的抓住，<strong>这样一拍即合的例子，蛰伏期远比你想的长，他们在技术上的付出也远比你想的多</strong>。</p>\r\n<p>希望大部分工程师不用等到 35 岁才想起来，自己的技术不能帮自己捕猎到优质的机会了。</p>\n</div>\n</div>\n\n\n<div class=\"view-more\"><a href=\"http://www.zhihu.com/question/29326842\">查看知乎讨论<span class=\"js-question-holder\"></span></a></div>\n\n</div>\n\n\n</div>\n</div><script type=“text/javascript”>window.daily=true</script>",
        "image_source": "Yestone 邑石网正版图库",
        "title": "为什么很多公司都不招大龄程序员？",
        "image": "https://pic3.zhimg.com/v2-a39cb81c02854b420720edc67df672fe.jpg",
        "share_url": "http://daily.zhihu.com/story/9680618",
        "js": [],
        "ga_prefix": "050107",
        "images": [
        "https://pic1.zhimg.com/v2-cd35e657a01abf0e11a769fe0635fcb4.jpg"
        ],
        "type": 0,
        "id": 9680618,
        "css": [
        "http://news-at.zhihu.com/css/news_qa.auto.css?v=4b3e3"
        ]
        }
         */

        @SerializedName("body") val body: String,
    @SerializedName("image_source") val imageSource: String,
    @SerializedName("title") val title: String,
    @SerializedName("image") val image: String,
    @SerializedName("share_url") val shareUrl: String,
    @SerializedName("js") val js: List<Any>,
    @SerializedName("ga_prefix") val gaPrefix: String,
    @SerializedName("images") val images: List<String>,
    @SerializedName("type") val type: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("css") val css: List<String>
)