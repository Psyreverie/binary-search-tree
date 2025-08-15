
async function submitNumbers() {
    const input = document.getElementById('numbers').value;
    const res = await fetch('/process-numbers', {
        method: 'POST',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: 'numbers=' + encodeURIComponent(input)
    });
    const data = await res.json();
    document.getElementById('output').textContent = JSON.stringify(data.tree, null, 2);
}


async function loadPreviousTrees() {
    const res = await fetch('/previous-trees');
    const data = await res.json();
    const container = document.getElementById('tree-list');
    container.innerHTML = '';
    data.forEach(tree => {
        const div = document.createElement('div');
        div.innerHTML = `<h3>${tree.inputNumbers}</h3><pre>${JSON.stringify(tree.treeJson, null, 2)}</pre>`;
        container.appendChild(div);
    });
}
