export function fetchInterceptor (url, method, body = null){

    const token = localStorage.getItem("token");
    const infoMethod = {
            method: method,
            headers:{
                "Authorization": `Bearer ${token}`
            }
    };
    if (body){
        infoMethod.body = body;
    };
    return fetch(url,infoMethod);
}
