window.addEventListener('load', function() {
    const searchButton = document.getElementById('searchButton');
    const clearButton = document.getElementById('clearButton');
    const searchInput = document.getElementById('searchInput');

    // Fetch and display records when the page loads
    displayRecordsFromServer();

    searchButton.addEventListener('click', function() {
        filterRecords();
    });

    clearButton.addEventListener('click', function() {
        searchInput.value = '';
        displayRecordsFromServer();
    });
});

function displayRecordsFromServer() {
    // Make a fetch request to your backend API to get customer information
    fetch('http://localhost:8080/api/clients')
        .then(response => response.json())
        .then(data => {
            // Display the received customer information in the table
            displayRecords(data);
        })
        .catch(error => {
            console.error('Error fetching customer records:', error);
        });
}

    searchButton.addEventListener('click', function() {
        filterRecords();
    });
    clearButton.addEventListener('click', function() {
        searchInput.value = '';
        displayRecordsFromLocalStorage();
    });

function filterRecords() {
    const records = JSON.parse(localStorage.getItem('loanRecords') || '[]');
    const searchValue = searchInput.value.toLowerCase();

    const filteredRecords = records.filter(record => {
        return record.name.toLowerCase().includes(searchValue);
    });

    displayRecords(filteredRecords);
}

function displayRecords(records) {
    const tbody = document.getElementById('recordTableBody');
    tbody.innerHTML = '';

    for (let record of records) {
        const tr = document.createElement('tr');
        tr.innerHTML = `
            <td>${record.firstName} ${record.lastName}</td>
            <td>${record.accountID}</td>
            <td>${record.date}</td>
            <td>
                <button class="details-button">Details</button>
            </td>
            <td>
                <div class="popup-content" style="display: none;">
                    <p>This is the pending credit line information.</p>
                </div>
            </td>
        `;
        tbody.appendChild(tr);
    }
}

const searchButton = document.getElementById('searchButton');
searchButton.addEventListener('click', function() {
    filterRecords();
});

// Get all the details buttons and their corresponding popup content divs
const detailButtons = document.querySelectorAll('.details-button');
const popupContents = document.querySelectorAll('.popup-content');

// Attach click event listeners to the buttons
detailButtons.forEach((button, index) => {
    button.addEventListener('click', () => {
        // Hide all other popups
        popupContents.forEach((content, i) => {
            if (i !== index) {
                content.style.display = 'none';
            }
        });

        // Toggle the display of the clicked button's popup content
        const popupContent = popupContents[index];
        if (popupContent.style.display === 'block') {
            popupContent.style.display = 'none';
        } else {
            popupContent.style.display = 'block';
        }
    });
});