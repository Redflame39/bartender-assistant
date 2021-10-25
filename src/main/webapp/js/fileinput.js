const inputSubmitBtn = document.querySelector('.fileUploadSubmit'),
    chooseFileInput = document.querySelector('.chooseFileInput');

function checkFileSelected() {
    if(chooseFileInput.files.length === 0 ){
        inputSubmitBtn.setAttribute('disabled', '');
    } else {
        inputSubmitBtn.removeAttribute('disabled');
    }
}

chooseFileInput.addEventListener('change', checkFileSelected);