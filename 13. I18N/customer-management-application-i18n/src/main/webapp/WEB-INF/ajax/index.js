$(document).ready(function() {
    $('#add-user').on('submit', function(event) {
        event.preventDefault();
        addNewUser();
    });

    $('#update-user').on('submit', function(event) {
        event.preventDefault();
        const id = $('#update-user').data('id');
        updateUser(id);
    });

    $('#display').on('click', function() {
        successHandler();
    });

    $('#display-create').on('click', function() {
        displayFormCreate();
    });

    $('#search-user').on('submit', function(event) {
        event.preventDefault();
        searchUser();
    });
});

async function searchUser() {
    const searchQuery = $('#search-input').val();

    try {
        const data = await $.ajax({
            type: "GET",
            url: `http://localhost:8080/api/users/search?search=${searchQuery}`,
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        });
        updateUserList(data);
        displayUserList();
    } catch (error) {
        console.error('Error searching user:', error);
    }
}

async function addNewUser() {
    const name = $('#create-name').val();
    const address = $('#create-address').val();

    const newUser = {
        name: name,
        address: address
    };

    try {
        await $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            type: "POST",
            data: JSON.stringify(newUser),
            url: "http://localhost:8080/api/users"
        });

        await successHandler();
        displayUserList();
    } catch (error) {
        console.error('Error adding new user:', error);
    }
}

function displayFormUpdate(user) {
    $('#update-user').data('id', user.id);
    $('#update-name').val(user.name);
    $('#update-address').val(user.address);
    $('#userList').hide();
    $('#add-user').hide();
    $('#update-user').show();
    $('#display-create').hide();
    $('#title').hide();
}

async function updateUser(id) {
    const name = $('#update-name').val();
    const address = $('#update-address').val();

    const updateUser = {
        id: id,
        name: name,
        address: address
    };

    try {
        await $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            type: "PUT",
            data: JSON.stringify(updateUser),
            url: `http://localhost:8080/api/users/${id}`
        });

        await successHandler();
        displayUserList();
    } catch (error) {
        console.error('Error updating user:', error);
    }
}

async function successHandler() {
    try {
        const data = await $.ajax({
            type: "GET",
            url: "http://localhost:8080/api/users"
        });
        updateUserList(data);
        displayUserList();
    } catch (error) {
        console.error('Error fetching user:', error);
    }
}

function displayFormCreate() {
    $('#userList').hide();
    $('#add-user').show();
    $('#update-user').hide();
    $('#display-create').hide();
    $('#title').hide();
}

function getuser(user) {
    const deleteText = $('#userList').data('delete-text');
    const updateText = $('#userList').data('update-text');
    return `<tr><td>${user.name}</td><td>${user.address}</td>` +
        `<td class="btn"><button class="deleteUser" onclick="deleteUser(${user.id})">${deleteText}</button></td>` +
        `<td class="btn"><button class="updateUser" onclick='displayFormUpdate(${JSON.stringify(user)})'>${updateText}</button></td></tr>`;
}

function updateUserList(data) {
    let content = '<table id="display-list" border="1"><tr>' +
        '<th>Name</th>' +
        '<th>Address</th>' +
        '<th>Delete</th>' +
        '<th>Update</th>' +
        '</tr>';

    data.forEach(user => {
        content += getuser(user);
    });

    content += "</table>";
    $('#userList').html(content);
}

function displayUserList() {
    $('#userList').show();
    $('#add-user').hide();
    $('#update-user').hide();
    $('#display-create').show();
    $('#title').show();
}

async function deleteUser(id) {
    try {
        await $.ajax({
            type: "DELETE",
            url: `http://localhost:8080/api/users/${id}`
        });
        successHandler();
    } catch (error) {
        console.error('Error deleting user:', error);
    }
}
