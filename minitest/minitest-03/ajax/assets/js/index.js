let index = 0;

function displayCategory(category) {
    return `<option value="${category.id}">${category.name}</option>`;
}

function displayCategoryHTML() {
    window.open("category.html", "_self");
}

function searchProduct() {
    let search = document.getElementById("search").value;
    $.ajax({
        type: "GET",
        url: `http://localhost:8080/products?search=${search}`,
        success: function (data) {
            let content = '<tr>\n' +
                '<th>Name</th>\n' +
                '<th>Price</th>\n' +
                '<th>Description</th>\n' +
                '<th>Category</th>\n' +
                '<th colspan="2">Action</th>\n' +
                '</tr>';
            if (data !== undefined) {
                for (let i = 0; i < data.length; i++) {
                    content += displayProduct(data[i]);
                }
            }

            document.getElementById("products").innerHTML = content;
            document.getElementById("form").hidden = true;
            document.getElementById("searchForm").reset();
        }
    });
    event.preventDefault();
}

function showCategories() {
    $.ajax({
        type: "get",
        url: `http://localhost:8080/categories`,
        success: function (data) {
            let content = `<label for="categories" class="form-label">Category:</label>`;
            content += `<select class="form-control" id="categories">`;
            for (let i = 0; i < data.length; i++) {
                content += displayCategory(data[i]);
            }
            content += ` </select>`;
            document.getElementById("categoriesList").innerHTML = content;
        }
    });
}

function deleteProduct(id) {
    $.ajax({
        type: "delete",
        url: `http://localhost:8080/products/${id}`,
        success: function () {
            listProducts();
            pageProducts();
        }
    });
}

function updateProduct() {
    let name = $("#name").val();
    let price = $("#price").val();
    let description = $("#description").val();
    let categoryId = $("#categories").val();
    let newProduct = {
        name: name,
        price: price,
        description: description,
        category: {
            id: categoryId,
        },
    };

    $.ajax({
        type: "post",
        url: `http://localhost:8080/products/${index}`,
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        data: JSON.stringify(newProduct),
        success: function () {
            listProducts();
            pageProducts();
        }
    })
}

function editProduct(id) {
    $.ajax({
        type: "GET",
        url: `http://localhost:8080/products/${id}`,
        success: function (data) {
            document.getElementById("form").hidden = false;
            index = data.id;
            $('#name').val(data.name);
            $('#price').val(data.price);
            $('#description').val(data.description);
            $('#categories').val(data.category.id);
            document.getElementById("form-button").onclick = function () {
                updateProduct();
            };
        }
    });
}

listProducts();
pageProducts()

function listProducts() {
    $.ajax({
        url: 'http://localhost:8080/products',
        method: 'GET',
        dataType: 'json',
        success: function (data) {
            size = data.length;
            pageProducts(size);
        }
    })

}

function pageProducts(size) {
    $(function () {
        let container = $('#demo');
        container.pagination({
            dataSource: 'http://localhost:8080/products',
            locator: 'items',
            totalNumber: size,
            pageSize: 2,
            callback: function (response, pagination) {
                let pageStart = (pagination.pageNumber - 1) * pagination.pageSize;
                let pageEnd = pageStart + pagination.pageSize;
                let pageItems = response.slice(pageStart, pageEnd);
                let content = '<tr>\n' +
                    '<th>Name</th>\n' +
                    '<th>Price</th>\n' +
                    '<th>Description</th>\n' +
                    '<th>Category</th>\n' +
                    '<th colspan="2">Action</th>\n' +
                    '</tr>';
                $.each(pageItems, function (index, item) {
                    content +=
                        `<tr>
                    <td>${item.name}</td>
                    <td>${item.price}</td>
                    <td>${item.description}</td>
                    <td>${item.category.name}</td>
                    <td><button class="btn btn-danger" onclick="deleteProduct(${item.id})">Delete</button></td>
                    <td><button class="btn btn-warning" onclick="editProduct(${item.id})">Edit</button></td>
                </tr>`
                })
                container.prev().html(content);
            }
        })
        document.getElementById("form").hidden = true;
    })
}

function addNewProduct() {
    let name = $("#name").val();
    let price = $("#price").val();
    let description = $("#description").val();
    let categoryId = $("#categories").val();
    let newProduct = {
        name: name,
        price: price,
        description: description,
        category: {
            id: categoryId,
        },
    };

    $.ajax({
        type: "post",
        url: `http://localhost:8080/products`,
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        data: JSON.stringify(newProduct),
        success: function () {
            listProducts();
            pageProducts();
        }
    })
}

function displayFormCreate() {
    document.getElementById("form").reset();
    document.getElementById("form").hidden = false;
    showCategories();
    document.getElementById("form-button").onclick = function () {
        addNewProduct();
    }
}

showCategories();