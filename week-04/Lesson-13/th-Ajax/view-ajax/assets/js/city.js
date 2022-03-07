function displayCities(city) {
    return `<tr>
                <td>${city.name}</td>
                <td><button class="btn btn-danger" onclick="deleteCity(${city.id})">Delete</button></td>
                <td><button class="btn btn-warning" onclick="editCity(${city.id})">Edit</button></td>
            </tr>`;
}

function displayCityHTML() {
    window.open("city.html");
}

function displayAllCity() {
    $.ajax({
        type: "get",
        url: `http://localhost:8080/cities`,
        success: function (data) {
            let content = '<tr>\n' +
                '<th>Name</th>\n' +
                '<th colspan="2">Action</th>\n' +
                '</tr>';
            if (data !== undefined) {
                for (let i = 0; i < data.length; i++) {
                    content += displayCities(data[i]);
                }
            }

            document.getElementById("customerCity").innerHTML = content;
            document.getElementById("formCity").hidden = true;
        }
    });
}

function addNewCity() {
    let name = $('#nameCity').val();
    let newCity = {
        name: name,
    }
    $.ajax({
        type: "post",
        url: "http://localhost:8080/cities",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        data: JSON.stringify(newCity),
        success: function() {
            displayAllCity();
        }

    });
}

function deleteCity(id) {
    $.ajax({
        type: "delete",
        url: `http://localhost:8080/cities/${id}`,
        success: function () {
            displayAllCity();
        }
    });
}

function displayFormCreateCity() {
    document.getElementById("formCity").reset()
    document.getElementById("formCity").hidden = false;
    document.getElementById("form-button-city").onclick = function () {
        addNewCity();
    }
}
let indexCity = 0;

function editCity(id) {
    $.ajax({
        type: "GET",
        url: `http://localhost:8080/cities/${id}`,
        success: function (data) {
            document.getElementById("formCity").hidden = false;
            indexCity = data.id;
            $('#nameCity').val(data.name);
            document.getElementById("form-button-city").onclick = function () {
                updateCity();
            };
        }
    });
}


function updateCity() {
    let name = $('#nameCity').val();
    let newCity = {
        name: name,
    };
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "PUT",
        data: JSON.stringify(newCity),
        url: `http://localhost:8080/cities/${indexCity}`,
        success: function () {
            displayAllCity()
        }
    });
    event.preventDefault();
}

displayAllCity()