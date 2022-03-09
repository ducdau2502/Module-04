let index = 0;

function displayProductHTML() {
    window.open("index.html", "_self");
}

function displayCategoriesList(category) {
    return `<tr>
                <td>${category.name}</td>
                <td><button class="btn btn-danger" onclick="deleteCategory(${category.id})">Delete</button></td>
                <td><button class="btn btn-warning" onclick="editCategory(${category.id})">Edit</button></td>
                <td><button class="btn btn-warning" onclick="viewDetail(${category.id})">Detail</button></td>
            </tr>`;
}

function viewDetail(id) {

}

function displayAllCategory() {
    $.ajax({
        type: "get",
        url: `http://localhost:8080/categories`,
        success: function (data) {
            let content1 = '<tr>\n' +
                '<th>Name</th>\n' +
                '<th colspan="3">Action</th>\n' +
                '</tr>';
            if (data !== undefined) {
                for (let i = 0; i < data.length; i++) {
                    content1 += displayCategoriesList(data[i]);
                }
            }

            document.getElementById("categoriesList").innerHTML = content1;
            document.getElementById("formCategory").hidden = true;
        }
    });
}

function addNewCategory() {
    let name = $('#nameCategory').val();
    let newCategory = {
        name: name,
    }
    $.ajax({
        type: "post",
        url: "http://localhost:8080/categories",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        data: JSON.stringify(newCategory),
        success: function() {
            displayAllCategory();
        }

    });
}

function deleteCategory(id) {
    $.ajax({
        type: "delete",
        url: `http://localhost:8080/categories/${id}`,
        success: function () {
            displayAllCategory();
        }
    });
}

function displayFormCreateCategory() {
    document.getElementById("formCategory").reset()
    document.getElementById("formCategory").hidden = false;
    document.getElementById("form-button-category").onclick = function () {
        addNewCategory();
    }
}

function editCategory(id) {
    $.ajax({
        type: "GET",
        url: `http://localhost:8080/categories/${id}`,
        success: function (data) {
            document.getElementById("formCategory").hidden = false;
            index = data.id;
            $('#nameCategory').val(data.name);
            document.getElementById("form-button-category").onclick = function () {
                updateCategory();
            };
        }
    });
}


function updateCategory() {
    let name = $('#nameCategory').val();
    let newCategory = {
        name: name,
    };
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "PUT",
        data: JSON.stringify(newCategory),
        url: `http://localhost:8080/categories/${index}`,
        success: function () {
            displayAllCategory()
        }
    });
    event.preventDefault();
}

displayAllCategory();