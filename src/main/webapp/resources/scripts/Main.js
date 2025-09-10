const submitStatus = document.getElementById('submitStatus');
const form = document.getElementById('input-form');
function addAdvice(msg) {
    const d = document.createElement('div');
    d.className = 'advice';
    d.textContent = msg;
    d.style.color = 'red';
    submitStatus.appendChild(d);
}
function clearAdvices() {
    document.querySelectorAll('.advice').forEach(el => el.remove());
}

form.addEventListener('submit', async(e) => {
    e.preventDefault();
    clearAdvices();
    if (!checkX || !checkR) {
        if (!checkX) addAdvice('Please pick correct X!');
        if (!checkR) addAdvice('Please pick R!');
        return;
    }
    form.submit();
});



