const approveBtn = document.querySelector('.updateApprovedStatusBtn');

async function updateApprovedStatus() {
    let id = approveBtn.id;
    let response = await fetch(`AjaxController?command=approve_cocktail&id=${id}`);
    let updated = await response.json();
    if (updated !== '{}') {
        if (updated) {
            document.getElementById(`cocktail${id}`).remove();
        }
    }
}

approveBtn.addEventListener('click', updateApprovedStatus)