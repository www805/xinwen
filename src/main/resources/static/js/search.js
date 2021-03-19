var app = new Vue({
    el:'#app',
    data:{
        hosturl: 'http://localhost',
        dataList:[]
        ,currPage: 1
        ,pageSize: 5
        ,pages: 0
        ,total: 0
        ,isloadShow: false
        ,keyword: ''
        ,type: ''
    },
    methods:{
        getSearchXinWen:function (currPage) {
            axios.post(getxinwen_manage().getSearchXinWen,{
                keyword: this.keyword,
                type: this.type,
                pageSize: this.pageSize,
                currPage: currPage
            }).then(function (response) {
                if(null != response.data && null != response.data.data){
                    let dataList = response.data.data.records
                    if(null != dataList && dataList.length > 0){
                        app.dataList = []
                        for (let i = 0; i < dataList.length; i++) {
                            const item = dataList[i]
                            if(isNotEmpty(item.content)){
                                item.content = item.content.replace(/<p>/g, "").replace(/<\/p>/g, "").replace(/\\n/g, "")
                            }
                            if(isNotEmpty(item.lastUpdateDatetime)){
                                const lastUpdateDatetime = new Date(item.lastUpdateDatetime)
                                item.pushTime = timeAgo(lastUpdateDatetime.getTime())
                            }else{
                                item.pushTime = ""
                            }
                            if(isNotEmpty(item.imgurls)){
                                const imgurls = item.imgurls.split(",")
                                if(imgurls.length > 0){
                                    item.imgurls = app.hosturl + imgurls[0]
                                }
                            }else{
                                item.imgurls = '/img/load_log.png';
                            }

                            if(isNotEmpty(item.content)){
                                const starNum = item.content.indexOf("<a href=")
                                if(starNum != -1){
                                    const removeStr = item.content.substring(starNum, item.content.indexOf("</a>"));
                                    item.content = item.content.replace(removeStr, "").replace("</a>", "");
                                }
                                item.content = item.content.replace("<br />", "").replace("&nbsp;", "");
                            }
                            app.dataList.push(item)
                        }

                        app.isloadShow = true
                    }
                    app.pages = response.data.data.pages
                    app.total = response.data.data.total
                }else{
                    console.log(response.data.message)
                    app.isloadShow = false
                }
            });
        }
    },

    created:function(){
        this.keyword = getQueryVariable("k")
        this.type = getQueryVariable("t")
        if(isNotEmpty(this.keyword) || isNotEmpty(this.type)){
            this.getSearchXinWen(this.currPage);
        }
    }
});