const usernameInputForm = document.getElementById('usernameInputForm'),
    emailInputForm = document.getElementById('emailInputForm'),
    passwordInputForm = document.getElementById('passwordInputForm'),
    rePasswordInputForm = document.getElementById('rePasswordInputForm'),
    submitBtn = document.getElementById('submitBtn');

const usernameInvalidFeedback = document.getElementById('usernameInvalidFeedback'),
    emailInvalidFeedback = document.getElementById('emailInvalidFeedback'),
    rePasswordInvalidFeedback = document.getElementById('rePasswordInvalidFeedback');

let isUsernameValid = false,
    isEmailValid = false,
    isRePasswordValid = false;

async function checkUsername() {
    let username = usernameInputForm.value;
    let response = await fetch(`AjaxController?command=username_is_free&username=${username}`);
    let isFree = await response.json();
    if (isFree) {
        usernameInvalidFeedback.innerHTML = '';
        isUsernameValid = true;
    } else {
        usernameInvalidFeedback.innerHTML = 'User with this username already exists.';
        isUsernameValid = false;
    }
    updateSubmitState();
}

async function checkEmail() {
    let email = emailInputForm.value;
    let response = await fetch(`AjaxController?command=email_is_free&email=${email}`);
    let isFree = await response.json();
    if (isFree) {
        emailInvalidFeedback.innerHTML = '';
        isEmailValid = true;
    } else {
        emailInvalidFeedback.innerHTML = 'Account with this email already registered';
        isEmailValid = false;
    }
    updateSubmitState();
}

function checkRePassword() {
    let password = passwordInputForm.value;
    let rePassword = rePasswordInputForm.value;
    if (password == rePassword) {
        rePasswordInvalidFeedback.innerHTML = '';
        isRePasswordValid = true;
    } else {
        rePasswordInvalidFeedback.innerHTML = 'Re-password doesn\'t match password';
        isRePasswordValid = false;
    }
    updateSubmitState();
}

function updateSubmitState() {
    if (isUsernameValid && isEmailValid && isRePasswordValid) {
        submitBtn.removeAttribute('disabled');
    } else {
        submitBtn.setAttribute('disabled', '');
    }
}

usernameInputForm.addEventListener('change', checkUsername);
emailInputForm.addEventListener('change', checkEmail);
rePasswordInputForm.addEventListener('change', checkRePassword);