$(document).ready(function() {
    $('#add-smartphone').on('submit', function(event) {
        event.preventDefault();
        addNewSmartPhone();
    });

    $('#display').on('click', function() {
        successHandler();
    });

    $('#display-create').on('click', function() {
        displayFormCreate();
    });
});

async function addNewSmartPhone() {
    // Lấy dữ liệu từ form HTML
    const producer = $('#producer').val();
    const model = $('#model').val();
    const price = $('#price').val();

    const newSmartphone = {
        producer: producer,
        model: model,
        price: price
    };

    // Gọi phương thức ajax
    try {
        await $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            type: "POST",
            data: JSON.stringify(newSmartphone),
            url: "http://localhost:8080/api/smartphones"
        });

        // Cập nhật danh sách smartphone và hiển thị nó
        await successHandler();
        displaySmartphoneList();
    } catch (error) {
        console.error('Error adding new smartphone:', error);
    }
}

async function successHandler() {
    try {
        const data = await $.ajax({
            type: "GET",
            url: "http://localhost:8080/api/smartphones"
        });
        updateSmartphoneList(data);
        displaySmartphoneList();
    } catch (error) {
        console.error('Error fetching smartphones:', error);
    }
}


function displayFormCreate() {
    $('#smartphoneList').hide();
    $('#add-smartphone').show();
    $('#display-create').hide();
    $('#title').hide();
}

function getSmartphone(smartphone) {
    return `<tr><td>${smartphone.producer}</td><td>${smartphone.model}</td><td>${smartphone.price}</td>` +
        `<td class="btn"><button class="deleteSmartphone" onclick="deleteSmartphone(${smartphone.id})">Delete</button></td></tr>`;
}

function updateSmartphoneList(data) {
    let content = '<table id="display-list" border="1"><tr>' +
        '<th>Producer</th>' +
        '<th>Model</th>' +
        '<th>Price</th>' +
        '<th>Delete</th>' +
        '</tr>';

    data.forEach(smartphone => {
        content += getSmartphone(smartphone);
    });

    content += "</table>";
    $('#smartphoneList').html(content);
}

function displaySmartphoneList() {
    $('#smartphoneList').show();
    $('#add-smartphone').hide();
    $('#display-create').show();
    $('#title').show();
}

async function deleteSmartphone(id) {
    try {
        await $.ajax({
            type: "DELETE",
            url: `http://localhost:8080/api/smartphones/${id}`
        });
        successHandler();
    } catch (error) {
        console.error('Error deleting smartphone:', error);
    }
}
