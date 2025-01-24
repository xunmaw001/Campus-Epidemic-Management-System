const base = {
    get() {
        return {
            url : "http://localhost:8080/xiaoyaunyiqingguanlixitong/",
            name: "xiaoyaunyiqingguanlixitong",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/xiaoyaunyiqingguanlixitong/front/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "校园疫情管理系统"
        } 
    }
}
export default base
