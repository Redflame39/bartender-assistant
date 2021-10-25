const approveBtn = document.querySelectorAll('.updateApprovedStatusBtn');

async function updateApprovedStatus(e) {
    let id = e.target.id;
    let response = await fetch(`AjaxController?command=approve_cocktail&id=${id}`);
    let updated = await response.json();
    if (updated !== '{}') {
        if (updated) {
            document.getElementById(`cocktail${id}`).remove();
        }
    }
}

approveBtn.forEach(btn => btn.addEventListener('click', updateApprovedStatus));