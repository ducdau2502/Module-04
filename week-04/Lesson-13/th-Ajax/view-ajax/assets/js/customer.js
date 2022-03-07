//create
function displayFormCreate() {
    document.getElementById("form").reset()
    document.getElementById("form").hidden = false;
    document.getElementById("formCity").hidden = true;

    showCity()
    document.getElementById("form-button").onclick = function () {
        addNewCustomer();
    }
}
let index = 0;

function displayCity(city) {
    return `<option value="${city.id}">${city.name}</option>`;
}

function showCity() {
    $.ajax({
        type: "get",
        url: `http://localhost:8080/cities`,
        success: function (data) {
            let content = "";
            for (let i = 0; i < data.length; i++) {
                content += displayCity(data[i]);
            }
            document.getElementById("cityListSelect").innerHTML = content;
        }
    });
}

function addNewCustomer() {
    let name  = $('#name').val();
    let email = $('#email').val();
    let cityId = $('#cityListSelect').val();
    let newCustomer = {
        name: name,
        email: email,
        city: {
            id: cityId,
            },
    };
    $.ajax({
        type: "post",
        url:"http://localhost:8080/customers",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        data: JSON.stringify(newCustomer),
        success: function () {
            showCustomer();
        }
    })
}

//Display
function displayCustomer(customer) {
        return `<tr>
                    <td>${customer.name}</td>
                    <td>${customer.email}</td>
                    <td>${customer.city.name}</td>
                    <td><button class="btn btn-danger" onclick="deleteCustomer(${customer.id})">Delete</button></td>
                    <td><button class="btn btn-warning" onclick="editCustomer(${customer.id})">Edit</button></td>
                </tr>`

}

function showCustomer() {
    $.ajax({
        type: "get",
        url: `http://localhost:8080/customers`,
        success: function (data) {
            let content = '<tr>\n' +
                '<th>Name</th>\n' +
                '<th>Email</th>\n' +
                '<th>City</th>\n' +
                '<th colspan="2">Action</th>\n' +
                '</tr>';
            if (data !== undefined) {
                for (let i = 0; i < data.length; i++) {
                    content += displayCustomer(data[i]);
                }
            }

            document.getElementById("customerList").innerHTML = content;
            document.getElementById("form").hidden = true;
        }
    });
}

function editCustomer(id) {
    $.ajax({
        type: "GET",
        url: `http://localhost:8080/customers/${id}`,
        success: function (data) {
            document.getElementById("form").hidden = false;
            index = data.id;
            $('#name').val(data.name);
            $('#email').val(data.email);
            $('#cityListSelect').val(data.city.id);
            document.getElementById("form-button").onclick = function () {
                updateCustomer();
            };
        }
    });
}

function updateCustomer() {
    let name = $('#name').val();
    let email = $('#email').val();
    let cityId = $('#cityListSelect').val();
    let newCustomer = {
        name: name,
        email: email,
        city: {
            id: cityId,
        },
    };
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "PUT",
        data: JSON.stringify(newCustomer),
        url: `http://localhost:8080/customers/${index}`,
        success: function () {
            showCustomer()
        }
    });
    event.preventDefault();
}

function deleteCustomer(id) {
    $.ajax({
        type: "delete",
        url: `http://localhost:8080/customers/${id}`,
        success: function () {
            showCustomer();
        }
    });
}

showCustomer();
showCity();