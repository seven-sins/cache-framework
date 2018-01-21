package com.lonecpp.nginx.lua.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author seven sins
 * @date 2018年1月14日 下午6:13:29
 */
@RestController
public class GoodsController {

	@GetMapping("/rest/goods")
	public Object get(Long id) {
		Goods goods = new Goods();
		goods.setId(id);
		goods.setProductName("ipad air");

		return goods;
	}

	public class Goods {
		private Long id;
		private String productName;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getProductName() {
			return productName;
		}

		public void setProductName(String productName) {
			this.productName = productName;
		}

	}
}
