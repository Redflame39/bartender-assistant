const usernameInput = document.getElementById('usernameInputForm'),
    errorPane = document.getElementById('error'),
    submitBtn = document.getElementById('submitBtn');

async function findUser() {
    let username = usernameInput.value;
    let response = await fetch(`AjaxController?command=username_is_free_to_edit&username=${username}`);
    let isFree = await response.json();
    if (isFree !== '{}') {
        if (isFree) {
            errorPane.innerText = '';
            submitBtn.removeAttribute('disabled');
        } else {
            errorPane.innerText = 'User with this username already exists';
            submitBtn.setAttribute('disabled', '');
        }
    }

}

usernameInput.addEventListener('blur', findUser);