$(document).ready(function(){
	getResults();
});

function formSubmit(){
  var hotel=document.getElementById("form1").value;
  var location=document.getElementById("form2").value;
  var url = "RestaurantReview.html?hotel="+hotel+"&location="+location;
  window.location.href=url;
}

function myMap() {
	var queryString = decodeURIComponent(window.location.search);
	queryString = queryString.substring(1);
  var _url= "/search?" + queryString;
	$.ajax({
    url: _url,
    type: 'GET',
		contentType: 'application/json; charset=utf-8',
    success: function(resultData) {
			// console.log(resultData);
			// console.log(resultData.coordinates);
			var myCenter = new google.maps.LatLng(resultData.coordinates.latitude,resultData.coordinates.longitude);
		  var mapCanvas = $('#map')[0];
		  var mapOptions = {center: myCenter, zoom: 15};
		  var map = new google.maps.Map(mapCanvas, mapOptions);
		  var marker = new google.maps.Marker({position:myCenter});
		  marker.setMap(map);
    }
  });
}

function makeUrl() {
	var queryString = decodeURIComponent(window.location.search);
	queryString = queryString.substring(1);
  return "/search?" + queryString;
}

function doAjax(_url) {
  return $.ajax({
    url: _url,
    type: 'GET',
		contentType: 'application/json; charset=utf-8',
    success: function(resultData) {
			// console.log(resultData);
			// console.log(resultData.imageUrl);
			$('#results').append(
				$('<li>').append(
						$('<div>').attr('class','contentHolder')
							.append($('<div>').attr('class', 'photoCol')
								.append($('<img>').attr('src',resultData.imageUrl).attr('class','dispImage'))
								) //can add pic
							.append($('<div>').attr('class', 'infoCol')
								.append($('<div>').attr('class','hotel')
									.append($('<div>').attr('class','name').append(resultData.name))
									.append($('<div>').attr('class','logo').append($('<img>').attr('src','img/yelp.png').attr('class','yelp').attr('onclick','loadReview()')))
									.append($('<div>').attr('class','rating').append(resultData.rating))
									.append($('<div>').attr('class','break').append('a'))
									.append($('<div>').attr('class','sfo').append($('<img>').attr('src','img/sfo.png').attr('class','yelp')))
									.append($('<div>').attr('class','rating').append('3.6'))
									.append($('<div>').attr('class','break').append('a'))
									.append($('<div>').attr('class','logo').append($('<img>').attr('src','img/yelp.png').attr('class','yelp')))
									.append($('<div>').attr('class','rating').append(resultData.score))
								))
							).attr('class','result leftLayout'));
    }
  });
}

function getResults() {
	var queryString = decodeURIComponent(window.location.search);
	queryString = queryString.substring(1);
  var _url= "/search?" + queryString;
	$.ajax({
    url: _url,
    type: 'GET',
		contentType: 'application/json; charset=utf-8',
    success: function(resultData) {
			var sentiment;
			if(resultData.score >= 0.7){
				sentiment='img/happy.png'
			} else if (resultData.score < 0.7 && resultData.score >= 0.4) {
				sentiment='img/neutral.png'
			} else {
				sentiment='img/sad.png'
			}
			// console.log(resultData);
			// console.log(resultData.imageUrl);
			$('#results').append(
				$('<li>').append(
						$('<div>').attr('class','contentHolder')
							.append($('<div>').attr('class', 'photoCol')
								.append($('<img>').attr('src',resultData.imageUrl).attr('class','dispImage'))
								) //can add pic
							.append($('<div>').attr('class', 'infoCol')
								.append($('<div>').attr('class','hotel')
									.append($('<div>').attr('class','name').append(resultData.name))
									.append($('<div>').attr('class','logo').append($('<img>').attr('src','img/yelp.png').attr('class','yelp').attr('onclick','loadReview()')))
									.append($('<div>').attr('class','rating').append(resultData.rating))
									.append($('<div>').attr('class','break').append('a'))
									.append($('<div>').attr('class','sfo').append($('<img>').attr('src','img/sfo.png').attr('class','yelp').attr('onclick','loadViolation()')))
									.append($('<div>').attr('class','rating').append('3.6'))
									.append($('<div>').attr('class','break').append('a'))
									.append($('<div>').attr('class','logo').append($('<img>').attr('src',sentiment).attr('class','yelp')))
									.append($('<div>').attr('class','rating').append(resultData.score))
								))
							).attr('class','result leftLayout'));
    }
  });
}

function loadReview(){
	$("#reviews").html("");
	var queryString = decodeURIComponent(window.location.search);
	queryString = queryString.substring(1);
  var _url= "/search?" + queryString;
	$.ajax({
    url: _url,
    type: 'GET',
		contentType: 'application/json; charset=utf-8',
    success: function(resultData) {
			// console.log(resultData);
			// console.log(resultData.reviews);
			var reviews=resultData.reviews;
			// console.log(reviews.length);
			for(i=0; i<reviews.length; i++){
				console.log(reviews[i].text);
				$('#reviews').append(
					$('<li>').append(
							$('<div>').attr('class','contentHolder')
								.append($('<div>').attr('class', 'reviews')
									.append(reviews[i].text))
								// .append($('<div>').attr('class', 'infoCol')
								// 	.append($('<div>').attr('class','hotel')
								// 		.append($('<div>').attr('class','name').append(resultData.name))
								// 		.append($('<div>').attr('class','logo').append($('<img>').attr('src','img/yelp.png').attr('class','yelp').attr('onclick','loadReview()')))
								// 		.append($('<div>').attr('class','rating').append(resultData.rating))
								// 		.append($('<div>').attr('class','break').append('a'))
								// 		.append($('<div>').attr('class','sfo').append($('<img>').attr('src','img/sfo.png').attr('class','yelp')))
								// 		.append($('<div>').attr('class','rating').append('3.6'))
								// 		.append($('<div>').attr('class','break').append('a'))
								// 		.append($('<div>').attr('class','logo').append($('<img>').attr('src','img/yelp.png').attr('class','yelp')))
								// 		.append($('<div>').attr('class','rating').append(resultData.score))
								// 	))
								).attr('class','result leftLayout'));
			}
    }
  });
}

function loadViolation(){
	var queryString = decodeURIComponent(window.location.search);
	queryString = queryString.substring(1);
  var _url= "/search?" + queryString;
	$.ajax({
    url: _url,
    type: 'GET',
		contentType: 'application/json; charset=utf-8',
    success: function(resultData) {
			$("#reviews").html("");
			// console.log(resultData);
			// console.log(resultData.violation);
			// for(i=0; i<reviews.length; i++){
			// 	console.log(reviews[i].text);
			// 	$('#reviews').append(
			// 		$('<li>').append(
			// 				$('<div>').attr('class','contentHolder')
			// 					.append($('<div>').attr('class', 'reviews')
			// 						.append(reviews[i].text))
			// 					).attr('class','result leftLayout'));
			// }
    }
  });
}

function ref(){
	$('#results ul').append(
    $('<li>').append(
        $('<div>').attr('class','contentHolder').append(
					$('<div>').attr('class', 'photoCol') //can add pic
					)).attr('class','result'));
}


function bigImg(x) {
   x.style.height = "17px";
	 x.style.width = "17px";
};

function normalImg(x) {
    x.style.height = "10px";
    x.style.width = "10px";
};
