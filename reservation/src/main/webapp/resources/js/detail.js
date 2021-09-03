document.addEventListener("DOMContentLoaded", function() {
	product.getData();
});

const product = {
	setId() {
		this.id = this.getParameter("id");
	},
	
	// JS에서 GET 파라미터 값 가져오기
	getParameter(name) {
		name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
		var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"), results = regex.exec(location.search);
		return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
	},
	
	getData() {
		var xhr = new XMLHttpRequest();
		
		xhr.addEventListener("load", function() {
			data = JSON.parse(xhr.responseText);
			upperArea.makeHtml(data);
			eventInfo.makeHtml(data.productPrices);
			review.makeHtml(data);
			detailInfo.makeHtml(data);
		});

		this.setId();
		xhr.open("GET", "api/products/" + this.id);
		xhr.send();
	}
}

const upperArea = {
	makeHtml(data) {
		this.imageNumHtml(data.productImages.length);
		this.productImageHtml(data);
		
		// 이미지가 2장인 경우, 슬라이드 버튼 생성
		if (data.productImages.length > 1) {
			this.slideBtnHtml();
		}
		
		this.productInfoHtml(data.displayInfo.productContent);
		this.unfoldClickEvent();
	},
	
	imageNumHtml(data) {
		var template = document.querySelector("#imageNum").innerText;
	    var bindTemplate = Handlebars.compile(template);
	    
		var item = {"imageNum" : data};
		
	    var resultHTML = bindTemplate(item);
	    document.querySelector(".figure_pagination").innerHTML = resultHTML;
	},
	
	productImageHtml(data) {
		var template = document.querySelector("#productImage").innerText;
		var bindTemplate = Handlebars.compile(template);
		var resultHTML = "";
		    
		for (var i = 0, len = data.productImages.length; i < len; i++) {
			var item = {
				"saveFileName" : data.productImages[i].saveFileName,
				"productDescription" : data.displayInfo.productDescription
			};
			
		    resultHTML += bindTemplate(item);
	    }
	    
	    document.querySelector(".visual_img").innerHTML += resultHTML;
	},
	
	slideBtnHtml() {
		var resultHTML = document.getElementById("slideBtn").innerHTML;
    	document.querySelector(".container_visual").innerHTML += resultHTML;
    	this.slideBtnClickEvent();
	},
	
	slideBtnClickEvent() {
		var ul = document.querySelector('.visual_img');
		var firstItemClone = ul.firstElementChild.cloneNode(true);
		var lastItemClone = ul.lastElementChild.cloneNode(true);
		ul.append(firstItemClone);
		ul.prepend(lastItemClone);
		ul.style.transform = "translate3d(-414px, 0px, 0px)"
		var index = 1;
		
		// prev 버튼 클릭 이벤트
		document.querySelector(".btn_prev").addEventListener("click", function(evt) {
			ul.style.transition = '0.2s';
			ul.style.transform = "translate3d(-" + 414 * (index - 1) + "px, 0px, 0px)";
			index--;
			
			if (index === 0) {
				setTimeout(function() {
					ul.style.transition = '0s';
					ul.style.transform = "translate3d(-828px, 0px, 0px)";
				}, 200)
						
				index = 2;
			}
			
			// 이미지 번호 변경
			if (index == 1) {
				document.querySelector("#image_num").innerText = "1";
				document.querySelector(".ico_arr6_lt").className = "spr_book2 ico_arr6_lt off";
				document.querySelector(".ico_arr6_rt").className = "spr_book2 ico_arr6_rt";
			} else {
				document.querySelector("#image_num").innerText = "2";
				document.querySelector(".ico_arr6_lt").className = "spr_book2 ico_arr6_lt";
				document.querySelector(".ico_arr6_rt").className = "spr_book2 ico_arr6_rt off";
			}
		});
		
		// next 버튼 클릭 이벤트
		document.querySelector(".btn_nxt").addEventListener("click", function(evt) {
			ul.style.transition = '0.2s';
			ul.style.transform = "translate3d(-" + 414 * (index + 1) + "px, 0px, 0px)";
			index++;
			
			if (index === 3) {
				setTimeout(function() {
					ul.style.transition = '0s';
					ul.style.transform = "translate3d(-414px, 0px, 0px)";
				}, 200)
						
				index = 1;
			}
			
			// 이미지 번호 변경
			if (index == 1) {
				document.querySelector("#image_num").innerText = "1";
				document.querySelector(".ico_arr6_lt").className = "spr_book2 ico_arr6_lt off";
				document.querySelector(".ico_arr6_rt").className = "spr_book2 ico_arr6_rt";
			} else {
				document.querySelector("#image_num").innerText = "2";
				document.querySelector(".ico_arr6_lt").className = "spr_book2 ico_arr6_lt";
				document.querySelector(".ico_arr6_rt").className = "spr_book2 ico_arr6_rt off";
			}
		});
	},
	
	productInfoHtml(data) {
		var template = document.querySelector("#productInfo").innerText;
	    var bindTemplate = Handlebars.compile(template);
	    
		var item = {"productContent" : data};
		
	    var resultHTML = bindTemplate(item);
	    document.querySelector(".store_details").innerHTML = resultHTML;
	},
	
	unfoldClickEvent() {
		document.querySelector("._open").addEventListener("click", function(evt) {
			document.querySelector(".store_details").className = "store_details";
			document.querySelector("._open").style.display = "none";
			document.querySelector("._close").style.display = "block";
    	});
    	
    	document.querySelector("._close").addEventListener("click", function(evt) {
			document.querySelector(".store_details").className = "store_details close3";
			document.querySelector("._close").style.display = "none";
			document.querySelector("._open").style.display = "block";
    	});
	}
}

const eventInfo = {
	makeHtml(data) {
		this.eventInfoHtml(data);
	},
	
	eventInfoHtml(data) {
		var content = '';
		
		for (var i = 0, len = data.length; i < len; i++) {
			if (data[i].discountRate != 0) {
				content += data[i].priceTypeName + '석 ' + data[i].discountRate + '%, ';
			}
		}
		
		content = content.substring(0, content.length - 2);
		var template = document.querySelector("#eventInfo").innerText;
		var bindTemplate = Handlebars.compile(template);
		var resultHTML = "";
		
		if (content != '') {
			var item = {
				"eventInfo" : '[네이버예약 특별할인]',
				"eventContent" : content
			};
			
		    resultHTML = bindTemplate(item);
	    } else {
	    	resultHTML = '<div class="in_dsc">진행중인 이벤트가 없습니다.</div>';
	    }
	    
	    document.querySelector(".event_info").innerHTML = resultHTML;
	}
}

const review = {
	makeHtml(data) {
		this.reviewSummaryHtml(data);
	},
	
	reviewSummaryHtml(data) {
		var template = document.querySelector("#reviewSummary").innerText;
	    var bindTemplate = Handlebars.compile(template);
	    var score = Math.round(data.averageScore * 10)
	    
		var item = {
			"starRange" : score / 10 * 20,
			"averageScore" : score / 10,
			"commentLength" : data.comments.length
		};
		
	    var resultHTML = bindTemplate(item);
	    document.querySelector(".grade_area").innerHTML = resultHTML;
	    
	    if (item.commentLength) {
	    	this.reviewContentHtml(data);
	    } else {
	    	document.querySelector(".list_short_review").innerHTML = '<li class="list_item">등록된 후기가 없습니다.</li>';
	    }
	},
	
	reviewContentHtml(data) {
		var template = document.querySelector("#reviewContent").innerText;
		var bindTemplate = Handlebars.compile(template);
		var resultHTML = "";
		var commentLen = data.comments.length
		
		// 리뷰 3개까지만 표시
		if (commentLen > 3) {
			commentLen = 3;
		}
		
		for (var i = 0; i < commentLen; i++) {
			var item = {
				"number" : i,
				"productDescription" : data.displayInfo.productDescription,
				"comment" : data.comments[i].comment,
				"score" : parseFloat(Math.round(data.comments[i].score * 10) / 10).toFixed(1),
				"reservationEmail" : data.comments[i].reservationEmail.split('@')[0].substring(0, 4) + '****',
				"reservationDate" : data.comments[i].reservationDate.split('T')[0].replaceAll('-', '.')
			};
			
			resultHTML = bindTemplate(item);
			document.querySelector(".list_short_review").innerHTML += resultHTML;
			
			// 리뷰 이미지가 있는 경우
			if (data.comments[i].commentImages.length != 0) {
				this.reviewImageHtml(data.comments[i].commentImages, i);
			}
		}
	},
	
	reviewImageHtml(data, index) {
		var template = document.querySelector("#thumbImage").innerText;
	    var bindTemplate = Handlebars.compile(template);
	    
		var item = {"saveFileName" : data[0].saveFileName};
		
	    var resultHTML = bindTemplate(item);
	    document.querySelector("#review" + index).insertAdjacentHTML('afterbegin', resultHTML);
	}
}

const detailInfo = {
	makeHtml(data) {
	    this.moreInfoHtml(data.displayInfo.productContent);
	    this.wayToComeHtml(data);
	    this.handleClickEvent();
	},
	
	moreInfoHtml(data) {
		var template = document.querySelector("#moreInfo").innerText;
	    var bindTemplate = Handlebars.compile(template);
	    
		var item = {"productContent" : data};
		
	    var resultHTML = bindTemplate(item);
	    document.querySelector(".detail_info_group").innerHTML = resultHTML;
	},
	
	wayToComeHtml(data) {
		var template = document.querySelector("#wayToCome").innerText;
	    var bindTemplate = Handlebars.compile(template);
	    
		var item = {
			"saveFileName" : data.displayInfoImage.saveFileName,
			"productDescription" : data.displayInfo.productDescription,
			"placeStreet" : data.displayInfo.placeStreet,
			"placeLot" : data.displayInfo.placeLot,
			"placeName" : data.displayInfo.placeName,
			"telephone" : data.displayInfo.telephone,
			"telephone" : data.displayInfo.telephone
		};
		
	    var resultHTML = bindTemplate(item);
	    document.querySelector(".box_store_info").innerHTML = resultHTML;
	},
	
	handleClickEvent() {
		document.querySelector(".info_tab_lst").addEventListener("click", function(evt) {
			var selected = "";
			var tagName = evt.target.tagName;
			
    		if(tagName === "LI") {
				selected = evt.target.className;
		    } else if(tagName === "A") {
				selected = evt.target.parentElement.className;
			} else if(tagName === "SPAN") {
				selected = evt.target.parentElement.parentElement.className;
			}
			
			selected = selected.split(' ')[1]
			
			if(selected === "_detail") {
				document.querySelector("._detail").childNodes[1].className = "anchor active";
				document.querySelector("._path").childNodes[1].className = "anchor";
				document.querySelector(".detail_area_wrap").className = "detail_area_wrap";
				document.querySelector(".detail_location").className = "detail_location hide";
			} else {
				document.querySelector("._detail").childNodes[1].className = "anchor";
				document.querySelector("._path").childNodes[1].className = "anchor active";
				document.querySelector(".detail_area_wrap").className = "detail_area_wrap hide";
				document.querySelector(".detail_location").className = "detail_location";
			}
    	});
	}
}