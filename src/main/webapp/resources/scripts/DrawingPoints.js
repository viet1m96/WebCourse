let hitPoints = [];
let missedPoints = [];

const rows = document.querySelectorAll('#final-results tr');

window.addEventListener('DOMContentLoaded', () => {
    hitPoints = [];
    rows.forEach((tr, i) =>
    {
        if(i <= 1) return;
        const tds = tr.querySelectorAll('td');
        const xText = tds[1].textContent.trim();
        const yText = tds[2].textContent.trim();
        const RText = tds[3].textContent.trim();
        const x = parseFloat(xText);
        const y = parseFloat(yText);
        const R = parseFloat(RText);
        if(tds[0].textContent.trim() === 'false') {
            missedPoints.push({x, y, R});
        } else {
            hitPoints.push({x, y, R});
        }
    });
});


window.addEventListener('DOMContentLoaded', () => {
    if(typeof window.drawGraph === 'function') {
        window.drawGraph();
    }
});

function drawPoints(points, type) {
    if(!Array.isArray(points) || points.length === 0) return;
    ctx.save();
    points.forEach(p => {
        ctx.beginPath();
        ctx.fillStyle   = type;
        ctx.strokeStyle = type;
        const scale = RDraw / p.R;
        const x = p.x * scale;
        const y = p.y * scale;
        const radius = 4 / zoom;
        ctx.arc(x, y, radius, 0, 2 * Math.PI);
        ctx.fill();
    });
    ctx.restore();
}