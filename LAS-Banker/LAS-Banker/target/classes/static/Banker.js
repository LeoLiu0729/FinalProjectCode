document.addEventListener("DOMContentLoaded", function () {
    const openAccountLink = document.querySelector("#openAccountLink");
    const openAccountModal = document.getElementById("myModal");
    const closeAccountLink = document.querySelector("#closeAccountLink");
    const closeAccountModal = document.getElementById("closeAccount");

    function openOpenAccountModal() {
        openAccountModal.style.display = "block";
    }

    function closeOpenAccountModal() {
        openAccountModal.style.display = "none";
    }

    function openCloseAccountModal() {
        closeAccountModal.style.display = "block";
    }

    function closeCloseAccountModal() {
        closeAccountModal.style.display = "none";
    }

    // Fetch and populate the table with customer information
    function populateTable() {
        fetch('http://localhost:8080/api/clients/getAll')
            .then(response => response.json())
            .then(data => {
                const tableBody = document.querySelector('.table tbody');
                tableBody.innerHTML = ''; // Clear existing table rows

                data.forEach(client => {
                    const row = document.createElement('tr');
                    row.innerHTML = `
                        <td>${client.firstName} ${client.lastName}</td>
                        <td>${client.accountID}</td>
                        <td>${client.balance}</td>
                    `;
                    tableBody.appendChild(row);
                });
            })
            .catch(error => {
                console.error('Error fetching customer information:', error);
            });
    }

    // Call the function to populate the table when the page loads
    populateTable();

    // Search functionality
    const searchBtn = document.getElementById('searchBtn');
    const searchBox = document.getElementById('searchBox');

    searchBtn.addEventListener('click', function () {
        const searchTerm = searchBox.value.trim();

        if (searchTerm !== '') {
            searchClientsByAccountID(searchTerm);
        } else {
            // If the search box is empty, display all clients
            populateTable();
        }
    });

    function searchClientsByAccountID(accountID) {
        fetch(`http://localhost:8080/api/clients/getByAccountID/${accountID}`)
            .then(response => response.json())
            .then(data => {
                const tableBody = document.querySelector('.table tbody');
                tableBody.innerHTML = ''; // Clear existing table rows

                if (data) {
                    const row = document.createElement('tr');
                    row.innerHTML = `
                        <td>${data.firstName} ${data.lastName}</td>
                        <td>${data.accountID}</td>
                        <td>${data.balance}</td>
                    `;
                    tableBody.appendChild(row);
                } else {
                    // Handle case where no client is found with the given account ID
                    console.log(`No client found with Account ID: ${accountID}`);
                    alert(`No client found with Account ID: ${accountID}`);
                }
            })
            .catch(error => {
                console.error('Error searching for client:', error);
            });
    }

    openAccountLink.addEventListener("click", openOpenAccountModal);
    const openAccountCloseButton = openAccountModal.querySelector(".close");
    openAccountCloseButton.addEventListener("click", closeOpenAccountModal);

    closeAccountLink.addEventListener("click", openCloseAccountModal);
    const closeAccountCloseButton = closeAccountModal.querySelector(".close");
    closeAccountCloseButton.addEventListener("click", closeCloseAccountModal);

    // Handle form submission for opening an account
    const openAccountForm = document.getElementById("openAccountForm");
    openAccountForm.addEventListener("submit", function (event) {
        event.preventDefault();

        const formData = new FormData(openAccountForm);

        // Convert form data to JSON manually, including proper handling of the date field
        const jsonData = {};
        formData.forEach((value, key) => {
            // Update the keys to match the names in your HTML form
            const updatedKey = key.charAt(0).toLowerCase() + key.slice(1);
            jsonData[updatedKey] = updatedKey === 'dob' ? new Date(value).toISOString() : value;
        });

        // Send data to the backend using fetch API
        fetch('http://localhost:8080/api/clients/create', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(jsonData),
        })
            .then(response => response.json())
            .then(data => {
                // Handle success, e.g., show a success message
                console.log('Client created successfully:', data);
                alert('Client created successfully!');
                closeOpenAccountModal();
            })
            .catch(error => {
                // Handle error, e.g., show an error message
                console.error('Error creating client:', error);
                alert('Error creating client. Please check your input.');
            });
    });

    // Handle form submission for closing an account
    const closeAccountForm = document.getElementById("closeAccountForm");
    closeAccountForm.addEventListener("submit", function (event) {
        event.preventDefault();

        const accountID = closeAccountForm.querySelector("#accNum").value;

        // Send request to the backend to close the account
        fetch(`http://localhost:8080/api/clients/close/${accountID}`, {
            method: 'DELETE',
        })
            .then(response => {
                if (response.ok) {
                    // Handle success, e.g., show a success message
                    console.log('Account closed successfully');
                    alert('Account closed successfully!');
                    closeCloseAccountModal();
                } else {
                    // Handle error, e.g., show an error message
                    console.error('Error closing account:', response.statusText);
                    alert('Error closing account. Please check the account ID.');
                }
            })
            .catch(error => {
                console.error('Error closing account:', error);
                alert('Error closing account. Please try again.');
            });
    });
});