const inputIn = document.getElementById("checkinDate");
const inputOut = document.getElementById("checkoutDate");
const nightsCount = document.getElementById("nightsCount");
const totalPrice = document.getElementById("totalPrice");
const modalWarning = new bootstrap.Modal(document.getElementById('warningModal'));
const modalConfirmation = new bootstrap.Modal(document.getElementById('reservationModal'));
const modalSuccess =  new bootstrap.Modal(document.getElementById('confirmationModal'));

const roomInfo = document.getElementById("roomInfo");
const idRoom = roomInfo.getAttribute("id-reservation");
const reservationPrice = document.getElementById("reservationPrice");
const pricePerNight = parseFloat(reservationPrice.getAttribute("reservation-price"));

function getJwtSubject(token) {
  const payloadBase64 = token.split('.')[1];

  const payloadJson = atob(payloadBase64.replace(/-/g, '+').replace(/_/g, '/'));

  const payload = JSON.parse(payloadJson);

  return payload.sub;
}

const token = localStorage.getItem("token");
const subject = getJwtSubject(token);

function updateValues() {
  const dateIn = new Date(inputIn.value);
  const dateOut = new Date(inputOut.value);

  fetch(`/api/v1/user/reservation/${token}`, {
        method: "GET",
        headers: {"Content-Type": "application/json"},
  })
  .then(response => {
    if(response.ok){
        return response.json();
    }
  })
  .then(responseJson =>{
    let id
    if(responseJson.user_type.name.equals("CLIENT")){
         id = responseJson.id_client;
         document.getElementById("confirmReservation").addEventListener("click", function(){
               fetch("/api/v1/room", {
                   method: "POST",
                   headers: {"Content-Type": "application/json"},
                   body: {
                     "id_client": id,
                     "id_room": idRoom
                   }
               })
               .then(response => {
                    if(response.ok){
                        modalConfirmation.hide();

                        modalSuccess.show();
                    }
               })
         })
    }else{
         id = responseJson.id_employee;
         console.log("No se puede realizar reservas desde una cuenta empleado")
         modalWarning.show();
    }

  })


  if (isNaN(dateIn.getTime()) || isNaN(dateOut.getTime())) {
    return;
  }

  const millis = dateOut - dateIn;
  const days = Math.ceil(millis / (1000 * 60 * 60 * 24));
  nightsCount.textContent = `${days}`;

  const newPrice = pricePerNight * days;
  totalPrice.textContent = `$${newPrice}`;

}

inputIn.addEventListener("change", updateValues);
inputOut.addEventListener("change", updateValues);


