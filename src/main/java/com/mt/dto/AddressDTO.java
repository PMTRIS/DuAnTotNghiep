package com.mt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
	private String cityID;
	private String cityName;
	private String districtID;
	private String districtName;
	private String wardID;
	private String wardName;
	

//	private AddressDTO getAddressFromApi(String cityId, String districtId, String wardId) {
//	    // Tạo RestTemplate để gọi API
//	    RestTemplate restTemplate = new RestTemplate();
//	    
//	    // Giả sử bạn có endpoint API để lấy thông tin tỉnh, quận, phường
//	    String cityApiUrl = "https://vapi.vnappmob.com/api/province/" + cityId; // URL cho tỉnh
//	    String districtApiUrl = "https://vapi.vnappmob.com/api/province/district/${cityId}" + districtId; // URL cho quận
//	    String wardApiUrl = "https://vapi.vnappmob.com/api/province/ward/${districtId}" + wardId; // URL cho phường
//
//	    // Lấy tên tỉnh
//	    ResponseEntity<String> cityResponse = restTemplate.getForEntity(cityApiUrl, String.class);
//	    String city = cityResponse.getBody(); // Giả sử API trả về tên tỉnh
//
//	    // Lấy tên quận
//	    ResponseEntity<String> districtResponse = restTemplate.getForEntity(districtApiUrl, String.class);
//	    String district = districtResponse.getBody(); // Giả sử API trả về tên quận
//
//	    // Lấy tên phường
//	    ResponseEntity<String> wardResponse = restTemplate.getForEntity(wardApiUrl, String.class);
//	    String ward = wardResponse.getBody(); // Giả sử API trả về tên phường
//
//	    return new AddressDTO(city, district, ward);
//	}
}
