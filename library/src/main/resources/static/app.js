async function loadMembers() {
    const response = await fetch('/api/members'); // Calls GETMapping
    const members = await response.json();

    const tableBody = document.getElementById('memberTableBody');
    tableBody.innerHTML = ''; // Clear existing data

    members.forEach(member => {
        const row = `<tr>
            <td>${member.memberID}</td>
            <td>${member.name}</td>
            <td>${member.cms}</td>
            <td>${member.department}</td>
        </tr>`;
        tableBody.innerHTML += row;
    });
}

// Load data when the page opens
loadMembers();

async function addMember() {
    const name = document.getElementById('nameInput').value;
    const cms = document.getElementById('cmsInput').value;
    const department = document.getElementById('deptInput').value;

    if (!department) {
            alert("Please select a department!");
            return;
        }

    const newMember = { name, cms, department };

    const response = await fetch('/api/members', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(newMember)
    });

    if (response.ok) {
        // Clear the inputs
        document.getElementById('nameInput').value = '';
        document.getElementById('cmsInput').value = '';

        // Refresh the table to show the new person
        loadMembers();
    } else {
        alert("Failed to add member. Check your backend console!");
    }
}