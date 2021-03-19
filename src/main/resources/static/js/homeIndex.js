var app = new Vue({
    el:'#app',
    data:{
        hosturl: 'http://localhost',
        dataList:[]
        ,currPage: 1
        ,isloadShow: false
        ,isloadTextShow: false
    },
    methods:{
        getXinWenList:function () {
            this.isloadShow = true
            axios.get(getxinwen_manage().getXinWenList,{
                params:{
                    currPage: this.currPage
                }
            }).then(function (response) {
                if(null != response.data && null != response.data.data){
                    let dataList = response.data.data.records
                    if(null != dataList && dataList.length > 0){
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

                        app.isloadShow = false
                        if(dataList.length > 0){
                            app.currPage++
                        }else{
                            app.isloadTextShow = true
                        }
                    }
                }else{
                    console.log(response.data.message)
                }
            });
        }
    },
    created:function(){
        this.getXinWenList();
    }
});