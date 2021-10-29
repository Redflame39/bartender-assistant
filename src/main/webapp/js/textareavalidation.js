const forbiddenSymbols = new RegExp('[`~\{\}<>/]');

function checkTextarea(textarea) {
    let text = textarea.value
        .replaceAll('[', '')
        .replaceAll(']','');
    console.log(text);
    if (text.search(forbiddenSymbols) != -1 || text.length == 0) {
        console.log('not ok');
        textarea.setCustomValidity('Text area contains forbidden symbols');
    } else {
        console.log('ok');
        textarea.setCustomValidity('');
    }
}