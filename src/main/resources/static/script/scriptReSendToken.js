function getTokenExpiration(token) {
  try {
    const payloadBase64 = token.split('.')[1];
    const payloadJson = atob(payloadBase64);
    const payload = JSON.parse(payloadJson);
    return payload.exp * 1000;
  } catch (e) {
    console.error("Token inválido:", e);
    return null;
  }
}
function refreshTokens(){
        fetch("/api/v1/auth/validate-auth", {
            method: "GET",
            headers: {
                "Authorization": `Bearer ${localStorage.getItem("token")}`
            }
        })
        .then(response =>{
            if(response.ok){
                fetch("/api/v1/auth/refresh-token", {
                    method: "POST",
                    headers: {"Content-Type": "application/json"},
                    body: JSON.stringify({refresh_token: localStorage.getItem("refreshToken")})
                })
                .then(response => {
                    if(response.ok){
                        console.log("refreshToken Enviado")
                    }
                    localStorage.clear();
                    return response.json();
                })
                .then(responseJson => {
                    console.log("actualizando tokens")
                    localStorage.setItem("refreshToken", responseJson.refresh_token);
                    localStorage.setItem("token", responseJson.token);
                    console.log(`Tokens actualizados:\n refreshToken: ${localStorage.getItem("refreshToken")}\n token: ${localStorage.getItem("token")}`);
                    refresh(localStorage.getItem("token"), refreshTokens);
                })
            }

        });
}
function refresh(token, refreshTokens) {
  const expiration = getTokenExpiration(token);
  const delay = expiration - Date.now() - 10_000;

  if (delay <= 0) {
    console.warn("Token ya expirado o a punto de expirar. Ejecutando refresh ahora.");
    refreshTokens();
  } else {
    console.log("Refresh programado en", Math.floor(delay / 1000), "segundos");
    setTimeout(refreshTokens, delay);
  }
}

refresh(localStorage.getItem("token"), refreshTokens);
