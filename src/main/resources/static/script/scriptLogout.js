const clsSession = document.getElementById("clsSession");
clsSession.addEventListener("click", function(){
    fetch("/api/v1/auth/logout", {
    method: "POST"
    })
    .then(() => {
        localStorage.clear();
        window.location.href = "/public/index";
    });
})