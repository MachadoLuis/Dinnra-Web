const clsSession = document.getElementById("clsSession");
clsSession.addEventListener("click", function(){
    fetch("/api/v1/auth/logout", {
    method: "POST"
    })
    .then(() => {
        localStorage.clear();
        window.location.href = "/public/index";
    });

    /*
    localStorage.clear();
    fetch("/api/v1/auth/validate-auth", {
        method: "GET",
        headers: {"Authorization": `Bearer `}
    })
    .then(response =>{
        if(!response.ok){
            window.location.href = "/public/index";
        }
    })*/

})