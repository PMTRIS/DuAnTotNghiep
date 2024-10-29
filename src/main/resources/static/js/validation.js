document.addEventListener("DOMContentLoaded", function () {
    // Fetch cities (tỉnh/thành phố)
    fetch('https://vapi.vnappmob.com/api/province/')
        .then(response => response.json())
        .then(data => {
            let citySelect = document.getElementById("city");
            citySelect.innerHTML = '<option value="0">Chọn Tỉnh/Thành phố</option>'; // Giá trị mặc định
            data.results.forEach(province => {
                let option = document.createElement("option");
                option.value = province.province_id;
                option.text = province.province_name;
                citySelect.add(option);
            });
        });

    document.getElementById("city").addEventListener("change", function () {
        let cityId = this.value;
        fetch(`https://vapi.vnappmob.com/api/province/district/${cityId}`)
            .then(response => response.json())
            .then(data => {
                let districtSelect = document.getElementById("district").querySelector("select");
                districtSelect.innerHTML = '<option value="0">Chọn Quận/Huyện</option>'; // Giá trị mặc định
                data.results.forEach(district => {
                    let option = document.createElement("option");
                    option.value = district.district_id; // Lưu ID
                    option.text = district.district_name; // Lưu tên huyện
                    districtSelect.add(option);
                });
            })
            .catch(error => console.error('Error fetching districts:', error));
    });

    document.getElementById("district").querySelector("select").addEventListener("change", function () {
        let districtId = this.value;
        let districtName = this.options[this.selectedIndex].text; // Lấy tên huyện
        fetch(`https://vapi.vnappmob.com/api/province/ward/${districtId}`)
            .then(response => response.json())
            .then(data => {
                let wardSelect = document.getElementById("ward");
                wardSelect.innerHTML = '<option value="0">Chọn Phường/Xã</option>'; // Giá trị mặc định
                data.results.forEach(ward => {
                    let option = document.createElement("option");
                    option.value = ward.ward_id; // Lưu ID
                    option.text = ward.ward_name; // Lưu tên phường/xã
                    wardSelect.add(option);
                });
            })
            .catch(error => console.error('Error fetching wards:', error));
    });
});


		
function validateForm() {
    let name = document.getElementById("name").value.trim();
    let phone = document.getElementById("phone").value.trim();
    let email = document.getElementById("email").value.trim();
    let address = document.getElementById("address").value.trim();
    let citySelect = document.getElementById("city");
    let cityId = citySelect.value;
    let cityName = citySelect.options[citySelect.selectedIndex].text;

    let districtSelect = document.getElementById("district").querySelector("select");
    let districtId = districtSelect.value;
    let districtName = districtSelect.options[districtSelect.selectedIndex].text;

    let wardSelect = document.getElementById("ward");
    let wardId = wardSelect.value;
    let wardName = wardSelect.options[wardSelect.selectedIndex].text;

    if (name === "" || phone === "" || email === "" || address === "" || cityId === "0" || districtId === "0" || wardId === "0") {
        alert("Vui lòng điền đầy đủ thông tin.");
        return false;
    }

    // Kiểm tra định dạng số điện thoại
    let phoneRegex = /^[0-9]{10}$/;
    if (!phoneRegex.test(phone)) {
        alert("Số điện thoại không hợp lệ.");
        return false;
    }

    // Kiểm tra định dạng email
    let emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
        alert("Email không hợp lệ.");
        return false;
    }

    // Tạo chuỗi địa chỉ
    let formattedString = `${address}, ${wardName}, ${districtName}, ${cityName}`;
    console.log(formattedString); // Kiểm tra chuỗi trước khi gửi

    let dataToSend = {
        name: name,
        phone: phone,
        email: email,
        address: formattedString 
    };

    // Gửi dữ liệu đến server
    fetch('http://localhost:8080/cart/success', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(dataToSend)
    })
    .then(response => response.json())
    .then(data => {
        console.log('Success:', data);
        // Xử lý kết quả thành công
    })
    .catch((error) => {
        console.error('Error:', error);
    });

    return true;
}
