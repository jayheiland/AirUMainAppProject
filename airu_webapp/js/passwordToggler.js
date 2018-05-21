// from: https://www.electrictoolbox.com/toggle-password-field-text-password/

(function() {

  try {
    // switch the password field to text, then back to password to see if it supports
    // changing the field type (IE9+, and all other browsers do). then switch it back.
    var passwordField = document.getElementById('password');
    passwordField.type = 'text';
    passwordField.type = 'password';

    // if it does support changing the field type then add the event handler and make
    // the button visible. if the browser doesn't support it, then this is bypassed
    // and code execution continues in the catch() section below
    var togglePasswordField = document.getElementById('togglePasswordField');
    togglePasswordField.addEventListener('click', togglePasswordFieldClicked, false);
    togglePasswordField.style.display = 'inline';

  } catch(err) {

  }
})();

function togglePasswordFieldClicked() {

  var passwordField = document.getElementById('password');
  var value = passwordField.value;

  if (passwordField.type == 'password') {
    passwordField.type = 'text';
  } else {
    passwordField.type = 'password';
  }

  passwordField.value = value;
}
