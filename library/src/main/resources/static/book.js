async function loadBooks() {
    const response = await fetch('/api/books'); // Calls GETMapping
    const book = await response.json();

    const tableBody = document.getElementById('bookTableBody');
    tableBody.innerHTML = ''; // Clear existing data

    book.forEach(book => {
        const row = `<tr>
            <td>${book.bookID}</td>
            <td>${book.title}</td>
            <td>${book.author}</td>
            <td>${book.genre}</td>
            <td>${book.isbn}</td>
            <td>${book.copies}</td>
            <td>
                <button class="btn btn-danger btn-sm" onclick="deleteBook('${book.bookID}', '${book.title}')">Delete</button>
            </td>
        </tr>`;
        tableBody.innerHTML += row;
    });
}

// Load data when the page opens
loadBooks();

async function addBook() {
    const title = document.getElementById('titleInput').value;
    const author = document.getElementById('authorInput').value;
    const genre = document.getElementById('genreInput').value;
    const isbn = document.getElementById('isbnInput').value;
    const copies = document.getElementById('copiesInput').value;

    if (!genre) {
            alert("Please select a genre!");
            return;
        }

    const newBook = { title, author, genre, isbn, copies};

    const response = await fetch('/api/books', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(newBook)
    });

    if (response.ok) {
        // Clear the inputs
        document.getElementById('titleInput').value = '';
        document.getElementById('authorInput').value = '';
        document.getElementById('genreInput').value = '';
        document.getElementById('isbnInput').value = '';
        document.getElementById('copiesInput').value = '';

        // Refresh the table to show the new person
        loadBooks();
    } else {
        alert("Failed to add book. Check your backend console!");
    }
}

function deleteBook(id, title) {

    confirmAction(`Are you sure you want to remove "${title}"?`, async () => {
        const response = await fetch(`/api/books/${id}`, { method: 'DELETE' });
        if (response.ok) {
            const msg = await response.text();
            showStatus("Success", msg);
            loadBooks();
        }
    });
}