document.addEventListener('DOMContentLoaded', function () {
  // Inicializar tooltips de Bootstrap
  var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
  tooltipTriggerList.map(function (tooltipTriggerEl) {
      return new bootstrap.Tooltip(tooltipTriggerEl);
  });

  // Inicializar dropdowns de Bootstrap
  var dropdownToggle = document.querySelectorAll('.dropdown-toggle');
  dropdownToggle.forEach(function (dropdown) {
      new bootstrap.Dropdown(dropdown);
  });

  // Actualizar el placeholder del input al hacer clic en una opción del menú
  var dropdownItems = document.querySelectorAll('.dropdown-item');
  dropdownItems.forEach(function (item) {
      item.addEventListener('click', function (event) {
          var input = document.getElementById('lugarInteres');
          input.placeholder = event.target.textContent.trim(); // Actualiza el placeholder del input
      });
  });
});