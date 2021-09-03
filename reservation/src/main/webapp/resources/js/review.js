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
			review.makeHtml(data);
		});

		this.setId();
		xhr.open("GET", "api/products/" + this.id);
		xhr.send();
	}
}

const review = {
	makeHtml(data) {
		this.topTitleHtml(data.displayInfo.productDescription);
		this.reviewSummaryHtml(data);
	},
	
	topTitleHtml(data) {
		var template = document.querySelector("#topTitle").innerText;
	    var bindTemplate = Handlebars.compile(template);
	    
		var item = {"title" : data};
		
	    var resultHTML = bindTemplate(item);
	    document.querySelector(".top_title").insertAdjacentHTML('beforeend', resultHTML);
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