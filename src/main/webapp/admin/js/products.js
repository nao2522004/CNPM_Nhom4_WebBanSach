/*--------------------------------------------------------
---------------------------------------------------------

                       Manager Product

---------------------------------------------------------
----------------------------------------------------------*/

//---------------Product Data--------------------//
// Lấy danh sách sản phẩm từ server
function fetchProducts() {
    $.ajax({
        url: '/WebBanQuanAo/admin/manager-products',
        type: 'GET',
        dataType: 'json',
        success: function (products) {
            const table = $("#products--table");

            // Xóa DataTables nếu đã được khởi tạo trước đó
            if ($.fn.DataTable.isDataTable(table)) {
                table.DataTable().destroy(); // Hủy DataTables
                table.find("tbody").empty(); // Xóa dữ liệu cũ
            }

            // Thêm tbody mới vào bảng
            const tbody = buildTableProduct(products);
            table.append(tbody);

            // Khởi tạo lại DataTables với phân trang và tìm kiếm
            table.DataTable({
                searching: true, // Kích hoạt tìm kiếm
                info: true, // Hiển thị thông tin tổng số bản ghi
                order: [[0, 'asc']], // Sắp xếp mặc định theo cột đầu tiên (Id)
                language: {
                    url: '//cdn.datatables.net/plug-ins/1.13.5/i18n/vi.json' // Ngôn ngữ Tiếng Việt
                },
                paging: true, // Kích hoạt phân trang
                pageLength: 5, // Số bản ghi mỗi trang
                lengthChange: true, // Kích hoạt thay đổi số lượng bản ghi mỗi trang
            });
        },
        error: function (xhr, status, error) {
            console.error('Lỗi khi lấy danh sách sản phẩm:', error);
            alert("Không thể lấy danh sách sản phẩm. Vui lòng thử lại sau.");
        }
    });
}

/*--------------------------------------------------------
---------------------------------------------------------

                       Add Product

---------------------------------------------------------
----------------------------------------------------------*/

// Hiển thị form thêm người dùng
function showAddProductForm() {
    const overlay = document.querySelector(".overlay-addProduct");
    overlay.style.display = "flex"; // Hiển thị lớp phủ
}


function hideAddProductForm() {
    const overlay = document.querySelector(".overlay-addProduct");
    const form = document.getElementById("add-product-form");
    form.reset(); // Xóa dữ liệu trong form
    overlay.style.display = "none";
}


async function createBook(event) {
    event.preventDefault();

    const form = document.getElementById('add-product-form');
    const formData = new FormData(form);

    const data = {
        title: formData.get('title'),
        author: formData.get('author'),
        description: formData.get('description'),
        publishedDate: formData.get('publishedDate'),
        price: parseFloat(formData.get('price')),
        stockQty: parseInt(formData.get('stockQty')),
        imageUrl: formData.get('imageUrl'),
        categoryIds: Array.from(
            form.querySelector('#categoryIds').selectedOptions,
            o => parseInt(o.value)
        )
    };

    console.log('Gửi lên server:', JSON.stringify(data));

    try {
        const resp = await fetch('/WebBanSach/api/books', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(data)
        });

        if (!resp.ok) {
            const errJson = await resp.json();
            console.error('Error JSON:', errJson);
            throw new Error(errJson.message || errJson.error || 'Unknown server error');
        }

        // Ở đây chắc chắn server trả đúng JSON { id: ... }
        const result = await resp.json();
        alert(`Thêm sách thành công! ID = ${result.id}`);
        form.reset();
    } catch (e) {
        console.error(e);
        alert('Có lỗi khi thêm sách: ' + e.message);
    }
}


window.addEventListener("DOMContentLoaded", hideAddProductForm);

// Xử lý sự kiện click ra ngoài form để ẩn form
document.addEventListener("click", function(event) {
    const overlay = document.querySelector(".overlay-addProduct");
    const form = document.getElementById("add-product-form");

    // Kiểm tra nếu form đang hiển thị và click không nằm trong form hoặc nút hiển thị form
    if (overlay.style.display === "flex" &&
        !form.contains(event.target) &&
        !event.target.closest("button")) {
        hideAddProductForm();
    }
});

