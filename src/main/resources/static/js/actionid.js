// var host = "http://127.0.0.1:8080";
var host = "";

function getxinwen_manage() {
    return {
        getXinWenList: host + "/xinwen/getXinWenList",
        getSearchXinWen: host + "/xinwen/getSearchXinWen",

    };
}