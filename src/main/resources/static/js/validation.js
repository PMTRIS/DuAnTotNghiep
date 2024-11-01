document.addEventListener("DOMContentLoaded", function () {
    const token = "7d0ed6b7-96b2-11ef-b17f-4603c5b85b86"; // Thay bằng token của bạn
    const shippingFeeEl = document.getElementById("shippingFee");

    // Lấy tỉnh thành từ API
    fetch('https://vapi.vnappmob.com/api/province/')
        .then(response => response.json())
        .then(data => {
            let citySelect = document.getElementById("city");
            data.results.forEach(province => {
                let option = document.createElement("option");
                option.value = province.province_id; // Lưu ID
                option.text = province.province_name; 
                citySelect.add(option);
            });
        });

    // Lấy quận/huyện khi chọn tỉnh
    document.getElementById("city").addEventListener("change", function () {
        let cityName = this.value;
        fetch(`https://vapi.vnappmob.com/api/province/district/${cityName}`)
            .then(response => response.json())
            .then(data => {
                let districtSelect = document.getElementById("district").querySelector("select");
                districtSelect.innerHTML = '<option value="0">Chọn Quận/Huyện</option>';
                data.results.forEach(district => {
                    let option = document.createElement("option");
                    option.value = district.district_id; // Lưu ID
                    option.text = district.district_name; // Hiển thị tên
                    districtSelect.add(option);
                });
            });
    });

    // Lấy phường/xã khi chọn quận/huyện
    document.getElementById("district").querySelector("select").addEventListener("change", function () {
        let districtId = this.value;
        fetch(`https://vapi.vnappmob.com/api/province/ward/${districtId}`)
            .then(response => response.json())
            .then(data => {
                let wardSelect = document.getElementById("ward");
                wardSelect.innerHTML = '<option value="0">Chọn Phường/Xã</option>';
                data.results.forEach(ward => {
                    let option = document.createElement("option");
                    option.value = ward.ward_id; // Lưu ID
                    option.text = ward.ward_name; // Hiển thị tên
                    wardSelect.add(option);
                });
            });
    });

	document.getElementById("ward").addEventListener("change", function () {
	    let citySelect = document.getElementById("city");
	    let districtSelect = document.getElementById("district").querySelector("select");
	    
	    let cityId = citySelect.value; // Lấy ID tỉnh/thành
	    let districtId = districtSelect.value; // Lấy ID quận/huyện
	    let wardId = this.value; // Lấy ID phường/xã

	    // Kiểm tra các giá trị ID
	    console.log("City ID:", cityId);
	    console.log("District ID:", districtId);
	    console.log("Ward ID:", wardId);
	    
	    let cityName = citySelect.options[citySelect.selectedIndex].text; // Tên tỉnh/thành
	    let districtName = districtSelect.options[districtSelect.selectedIndex].text; // Tên quận/huyện
	    let wardSelect = document.getElementById("ward");
	    let wardName = wardSelect.options[wardSelect.selectedIndex].text; // Tên phường/xã

	    // Kiểm tra tên
	    console.log("City Name:", cityName);
	    console.log("District Name:", districtName);
	    console.log("Ward Name:", wardName);
		
		const token = "7d0ed6b7-96b2-11ef-b17f-4603c5b85b86"; // Thay bằng token của bạn
		const shopId = "5424124";
		fetch(`https://online-gateway.ghn.vn/shiip/public-api/v2/shipping-order/soc=${wardId}&province=${cityId}&district=${districtId}`, {
			        headers: {
			            'Authorization': `Bearer ${token}`,
						'ShopId': shopId,
			        }
			    })
			    .then(response => response.json())
			    .then(data => {
			        if (data.code === 200) {
			            shippingFeeEl.textContent = `${data.data.total} VNĐ`;
			        } else {
			            shippingFeeEl.textContent = "Không thể tính phí";
			            console.error("Lỗi tính phí:", data.message);
			        }
			    })
			    .catch(error => console.error("Error fetching shipping fee:", error));
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
	console.log("City Name:", cityName);
	console.log("District Name:", districtName);
	console.log("Ward Name:", wardName);
    if (name === "" || phone === "" || email === "" || address === "" || cityId === "0" || districtId === "0" || wardId === "0") {        
        return false;
    }

	let nameRegex = /^[A-Za-zÀ-ỹ\s]+$/;
	if (!nameRegex.test(name)) {
	        return false;
	}
	
    let phoneRegex = /^[0-9]{10}$/;
    if (!phoneRegex.test(phone)) {
        return false;
    }
     let emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
        return false;
    }

    // Tạo chuỗi địa chỉ
    let formattedString = `${address}, ${wardName}, ${districtName}, ${cityName}`;

    let dataToSend = {
        name: name,
        phone: phone,
        email: email,
        address: formattedString
    };
    return true;
}
