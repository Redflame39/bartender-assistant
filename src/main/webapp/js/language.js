function getUserLanguage() {
    let lang = navigator.language;
    fetch(`http://localhost:8080/AjaxController&command=locale&locale=${lang}`,
        {
            method: 'POST'
        })
}

document.addEventListener('DOMContentLoaded', getUserLanguage)