document.addEventListener("DOMContentLoaded", function() {
	getCategories();
	getPromotions();
	getProducts("0", 0);
});

var categoryId = "0"  // 현재 선택된 카테고리 id
var count = 0  // 현재 화면에 표시된 product 개수

document.querySelector(".event_tab_lst").addEventListener("click", function(evt) {
	var newCategoryId = ""
	var tagName = evt.target.tagName;
	
	if(tagName === "LI") {
		newCategoryId = evt.target.dataset.category;
    } else if(tagName === "A") {
		newCategoryId = evt.target.parentElement.dataset.category;
	} else if(tagName === "SPAN") {
		newCategoryId = evt.target.parentElement.parentElement.dataset.category;
	} else {
		newCategoryId = categoryId;
	}
	
	if(categoryId != newCategoryId) {
		document.querySelector("#category_" + categoryId).childNodes[1].className = 'anchor';
		document.querySelector("#category_" + newCategoryId).childNodes[1].className = 'anchor active';
		categoryId = newCategoryId;
		count = 0
		
		document.querySelector("#left_lst_box").innerHTML = "";
		document.querySelector("#right_lst_box").innerHTML = "";
		getProducts(categoryId, count);
	}
});

document.querySelector(".more").addEventListener("click", function(evt) {
	getProducts(categoryId, count);
});

function getCategories() {
	var xhr = new XMLHttpRequest();

	xhr.addEventListener("load", function() {
		data = JSON.parse(xhr.responseText);
		makeCategoryHtml(data)
	});

	xhr.open("GET", "api/categories");
	xhr.send();
}

function getPromotions() {
	var xhr = new XMLHttpRequest();

	xhr.addEventListener("load", function() {
		data = JSON.parse(xhr.responseText);
		makePromotionHtml(data)
		promotionSliding(data.items.length)
	});

	xhr.open("GET", "api/promotions");
	xhr.send();
}

function getProducts(id, start) {
	var xhr = new XMLHttpRequest();

	xhr.addEventListener("load", function() {
		data = JSON.parse(xhr.responseText);
		makeProductHtml(data)
	});

	if(id === "0") {
		xhr.open("GET", "api/products?start=" + start);
	} else {
		xhr.open("GET", "api/products?categoryId=" + id + "&start=" + start);
	}
	xhr.send();
}

function makeCategoryHtml(data) {
	var html = document.getElementById("categoryItem").innerHTML;
	var resultHTML = "";

	for (var i = 0, len = data.items.length; i < len; i++) {
		resultHTML += html.replace("{categoryId}", data.items[i].id)
						  .replace("{categoryId}", data.items[i].id)
						  .replace("{categoryName}", data.items[i].name)
	}

	document.querySelector(".event_tab_lst").insertAdjacentHTML('beforeend', resultHTML);
}

function makePromotionHtml(data) {
	var html = document.getElementById("promotionItem").innerHTML;
	var resultHTML = "";

	for (var i = 0, len = data.items.length; i < len; i++) {
		resultHTML += html.replace("{productImageUrl}", data.items[i].productImageUrl)
	}

	document.querySelector(".visual_img").insertAdjacentHTML('beforeend', resultHTML);
}

function promotionSliding(length) {
	var ul = document.querySelector('.visual_img');
	var firstItemClone = ul.firstElementChild.cloneNode(true);
	ul.appendChild(firstItemClone);
	var index = 0;
	
	setInterval(function() {
		ul.style.transition = '0.2s';
		ul.style.transform = "translate3d(-" + 414 * (index + 1) + "px, 0px, 0px)";
		index++;
		
		if (index === length) {
			setTimeout(function() {
				ul.style.transition = '0s';
				ul.style.transform = "translate3d(0px, 0px, 0px)";
			}, 201)
					
			index = 0;
		}
		
	}, 200 * length);
}

function makeProductHtml(data) {
	document.querySelector(".pink").innerHTML = data.totalCount+"개";
	var html = document.getElementById("productItem").innerHTML;
	var resultHTML;

	for (var i = 0, len = data.items.length; i < len; i++) {
		resultHTML = "";
		resultHTML += html.replace("{displayInfoId}", data.items[i].displayInfoId)
						  .replace("{productDescription}", data.items[i].productDescription)
						  .replace("{productDescription}", data.items[i].productDescription)
						  .replace("{productImageUrl}", data.items[i].productImageUrl)
						  .replace("{placeName}", data.items[i].placeName)
						  .replace("{productContent}", data.items[i].productContent)
		
		if(i % 2 == 0) {
			document.querySelector("#left_lst_box").insertAdjacentHTML('beforeend', resultHTML);
		} else {
			document.querySelector("#right_lst_box").insertAdjacentHTML('beforeend', resultHTML);
		}
	}
	
	count += 4
	
	if(count < data.totalCount) {
		document.querySelector(".more").style.display = "block";
	} else {
		document.querySelector(".more").style.display = "none";
	}
}