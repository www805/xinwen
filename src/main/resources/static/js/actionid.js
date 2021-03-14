var host = "http://127.0.0.1:8080";

function getxinwen_manage() {
    return {
        getXinWenList: host + "/xinwen/getXinWenList",
        getSearchXinWen: host + "/xinwen/getSearchXinWen",

    };
}