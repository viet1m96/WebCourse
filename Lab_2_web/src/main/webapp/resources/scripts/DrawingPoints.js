let hitPoints = [];
let missedPoints = [];

const rows = document.querySelectorAll('#final-results tr');



window.addEventListener('DOMContentLoaded', () => {
    if(window.savedR !== null && window.savedR !== "") {
        const savedR = window.savedR;
        const el = document.querySelector('input[name="R"][type="checkbox"][value="' + savedR + '"]');
        if (el && !el.checked) {
            el.checked = true;
            el.dispatchEvent(new Event('change', { bubbles: true }));
        }
    }
});

function distinguishPoints() {
    hitPoints = [];
    missedPoints = [];
    rows.forEach((tr, i) =>
    {
        if(i < 1) return;
        const tds = tr.querySelectorAll('td');
        const xText = tds[1].textContent.trim();
        const yText = tds[2].textContent.trim();
        const x = parseFloat(xText);
        const y = parseFloat(yText);
        if(tds[0].textContent.trim() === 'false' || tds[0].textContent.trim() === 'true') {
            if(isHit(x, y)) {
                hitPoints.push({x, y});
            } else {
                missedPoints.push({x, y});
            }
        }
    });
}

function drawPointsOperation() {
    distinguishPoints();
    drawPointsWithColor(hitPoints, 'green');
    drawPointsWithColor(missedPoints, 'red');
}

function drawPointsWithColor(points, type) {
    if(!Array.isArray(points) || points.length === 0) return;
    ctx.save();
    points.forEach(p => {
        ctx.beginPath();
        ctx.fillStyle   = type;
        ctx.strokeStyle = type;
        const x = p.x * RDraw;
        const y = p.y * RDraw;
        const radius = 4 / zoom;
        ctx.arc(x, y, radius, 0, 2 * Math.PI);
        ctx.fill();
    });
    ctx.restore();
}

function isHit(x, y) {
    return checkCircle(x, y) || checkSquare(x, y) || checkTriangle(x, y);
}

function checkSquare(x, y) {
    return x <= 0 && y >= 0 && x >= -realR / 2 && y <= realR;
}

function checkCircle(x, y) {
    return x >= 0 && y >= 0 && (x * x + y * y <= realR * realR);
}

function checkTriangle(x, y) {
    return x >= 0 && y <= 0 && ((x - 2 * y) <= realR);
}
